Flame = Class{name="Flame", function(self, p)
	self.light = Light(p:clone(), 270, 1, {255,230,180})
	self.vel = vector(0,0)
	self.t = 0
	self.last = 0
end}

function Flame:predraw(objects)
	self.light:draw()
	love.graphics.setColor(0,0,0)

    
    for _,o in ipairs(objects) do
        function go()
            love.graphics.polygon('fill', self.light:castShadow(o))
            print("I HATH CREATED A SHADOW AT %d", self.light:castShadow(o))
        end
        if pcall(go) then
            --print("no errors")
        else
            --print("errors, errors everywhere")
        end
    end
end


function Flame:draw()
	love.graphics.setColor(0,0,0)
	-- love.graphics.circle('line', self.light.pos.x, self.light.pos.y, 10)
	-- black out the rest
	if not debug.noDarkening then
		self.light:drawMask()
	end
end

function Flame:update(dt)
	self.t = self.t + dt
	local a = vector(0,0)
	if love.keyboard.isDown('left') then
		a.x = -1
	elseif love.keyboard.isDown('right') then
		a.x = 1
	end
	if love.keyboard.isDown('up') then
		a.y = -1
	elseif love.keyboard.isDown('down') then
		a.y = 1
	end


	self.vel = self.vel / 1.08 + a * 1200 * dt
	self.vel.y = self.vel.y + 5.2 * math.sin(math.pi * self.t)
	self.vel.x = self.vel.x + 10.8 * math.cos(math.pi * self.t)


	local pos = self.light.pos + self.vel * dt


	pos.x = math.max(0, math.min(800, pos.x))
	pos.y = math.max(0, math.min(600, pos.y))

    flamepos = vector(pos.x,pos.y)



	-- TODO: use a spatialhash for this
	local move = vector(0,0)
	for _,o in ipairs(objects) do
		local collide, separatingVector = o:intersectsCircle(pos, 12)
		if collide then
			move = move + separatingVector
		end
	end

	self.light.pos = pos + move

	-- flicker light
	self.last = math.min(1, math.max(0, 2*math.random()-1 + self.last * self.last))
	self.light.intensity = .98 + .04 * self.last
	self.light.range = self.light.intensity / 1.12 +3
end