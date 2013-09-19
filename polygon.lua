local function unp(v, ...)
	if not v then return end
	return v.x, v.y, unp(...)
end
Polygon = Class{function(self, vertices)
	self.vertices = {}
	for i,v in ipairs(vertices) do
		self.vertices[i] = v:clone()
	end
	self.points = {unp(unpack(vertices))}

	local function edge(p1,p2)
		return {p1=p1,p2=p2, len = (p1-p2):len()/2, center=(p1+p2)/2, normal = (p2-p1):perpendicular():normalize_inplace()}
	end
	self.edges = {}
	for i = 1,#self.vertices do
		k = i == #self.vertices and 1 or i + 1
		self.edges[i] = edge(self.vertices[i], self.vertices[k])
	end

	self.indicies = {}
	for i,v in ipairs(self.vertices) do
		self.indicies[v] = i
	end
end}

function Polygon:draw()
	if not debug.showWireframe then
		love.graphics.setColor(200,120,30)
		love.graphics.polygon('fill', self.points)
	end
	if debug.showNormals then
		love.graphics.setColor(255,100,100)
		for _,e in ipairs(self.edges) do
			local k = e.center + e.normal * 20
			love.graphics.line(e.center.x,e.center.y,k.x,k.y)
		end
	end
	if debug.showWireframe or self.flagColission then
		love.graphics.setColor(100,255,100)
		love.graphics.polygon('line', self.points)
	end
end

function Polygon:contains(point)
	for _,e in ipairs(self.edges) do
		if e.normal * (point - e.p1) > 0 then return false end
	end
	return true
end

function Polygon:intersectsCircle(center, radius)
	if self:contains(center) then return true, vector(0,0) end
	local function segmentDistanceSqToPoint(e, p)
		local dsq_line = (p - e.center):projectOn(e.normal):len2() -- distance to line
		local lsq_line = (p - e.center):projectOn(e.normal:perpendicular()):len2() -- projection on line
		-- point over segment
		if lsq_line < e.len*e.len then return dsq_line end
		lsq_line = math.sqrt(lsq_line) - e.len -- excess
		-- point on line
		if dsq_line == 0 then return lsq_line*lsq_line end
		-- neither -> pythagoras
		return dsq_line + lsq_line*lsq_line
	end
	-- center outside of circle. check distance to each segment
	for _,e in ipairs(self.edges) do
		local dist = segmentDistanceSqToPoint(e, center)
		if e.normal * (center - e.p1) > 0 and dist <= radius*radius then
			return true, e.normal * (radius-math.sqrt(dist))
		end
	end
	return false
end
