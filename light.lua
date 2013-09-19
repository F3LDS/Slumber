Light = Class{function(self, pos, range, intensity, color)
	self.pos = pos
	self.range = range and range / 300 or 1
	self.intensity = intensity or 1
	self.color = color or {255,255,255}
end}

function Light:castShadow(poly)
	local inRange = false
	local vertices = {}
	for _,e in ipairs(poly.edges) do
		local d = (e.p1 + e.p2)/2
		if (self.pos - d):len2() < (self.range * 300)^2 then inRange = true end
		if (d - self.pos) * e.normal > 0 then
			vertices[#vertices+1] = e.p1
			vertices[#vertices+1] = e.p2

			vertices[#vertices+1] = e.p1 + 800 * (e.p1 - self.pos):normalized()
			vertices[#vertices+1] = e.p2 + 800 * (e.p2 - self.pos):normalized()
		end
	end

	if not inRange then return {} end

	vertices = ConvexHull(vertices)
	local poly = {}
	for i,v in ipairs(vertices) do
		poly[#poly+1] = v.x
		poly[#poly+1] = v.y
	end

	return poly
end

local light_img, light_mask

function Light:load()
	local light_img_id = love.image.newImageData(600,600)
    light_img_id:mapPixel(function(x,y) x,y = x/300-1,y/300-1
        if x*x+y*y >= 1 then return 0,0,0,0 end
        local i = (1 - math.min(1, math.sqrt(x*x+y*y)) ^ 0.7) * 255
        return 255,255,255,i
    end)
    light_img = love.graphics.newImage(light_img_id)

    light_mask = love.image.newImageData(600,600)
    light_mask:mapPixel(function(x,y)
        local _,_,_,a = light_img_id:getPixel(x,y)
        return 0,0,0,255-a
    end)
    light_mask = love.graphics.newImage(light_mask)
end


function Light:draw()

	love.graphics.setColor(self.color[1], self.color[2], self.color[3])
	love.graphics.draw(light_img, self.pos.x, self.pos.y, 0, self.range,self.range, 300,300)
end

function Light:drawMask()
	love.graphics.setColor(0,0,0)
	love.graphics.draw(light_mask, self.pos.x, self.pos.y, 0, self.range,self.range,300,300)
	local ul = self.pos - vector(self.range,self.range)*300
	if ul.x > 0 then love.graphics.rectangle('fill', 0,0,ul.x,600) end
	if ul.y > 0 then love.graphics.rectangle('fill', 0,0,800,ul.y) end

	local lr = self.pos + vector(self.range,self.range)*300
	if lr.x < 800 then love.graphics.rectangle('fill', lr.x,0,800,600) end
	if lr.y < 600 then love.graphics.rectangle('fill', 0,lr.y,800,600) end

	if self.intensity < 1 then
		love.graphics.setColor(0,0,0,(1-self.intensity)*255)
		love.graphics.rectangle('fill', ul.x,ul.y,(lr-ul):unpack())
	end
end