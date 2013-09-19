require 'algorithms'
require 'class'
require 'light'
require 'polygon'
require 'flame'
require 'player'

debug.showNormals = false
debug.showWireframe = false


--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==
-- commencing holy trinity
--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==
-- functional magic
local function map(func, tbl) for k,v in pairs(tbl) do tbl[k] = func(v) end return tbl end
local function conc(tbl1,tbl2,...)
	if not tbl2 then return tbl1 end
	for i,v in ipairs(tbl2) do tbl1[#tbl1+1] = v end
	return conc(tbl1,...)
end

function love.load()
    playerpos = vector(0,0)
    flamepos = vector(0,0)

    part1 = love.graphics.newImage("part1.png");


    p = love.graphics.newParticleSystem(part1, 1000)
    p:setEmissionRate(130)
    p:setSpeed(100, 60)
    p:setSizes(1, .5)
    p:setColors(220, 105, 20, 255, 194, 30, 18, 0)
    p:setPosition(0, 0)
    p:setLifetime(999)
    p:setParticleLife(.2, .7)
    p:setDirection(4.7)
    --p:setSpread()
    --p:setTangentialAcceleration(1000)
    --p:setRadialAcceleration(-1369, 0)
    --p:stop()



	love.graphics.setBackgroundColor(0,0,0)
	objects = map(Polygon, conc(
			SplitConvex{vector(100,100), vector(130,70), vector(150,120), vector(120,160), vector(130,110) },
			SplitConvex{vector(607,53), vector(729,19), vector(790,135), vector(709,220), vector(707,108), vector(583,97), vector(624,75)},
			{ConvexHull{vector(250,250), vector(260,350), vector(320,410), vector(400,240), vector(300,190) },
			 ConvexHull{vector(500,200), vector(550,249), vector(490,210), vector(510,50) },
             ConvexHull{vector(518,346), vector(456,455), vector(525,520), vector(592,475), vector(587,365) }}))

	flame = Flame(vector(100,300))
    flame.light:load()
    player = Player(100, 800)
    player:load()

	love.graphics.setLine(2)



end

local points = {}
function love.draw()
	flame:predraw(objects)

	for _,o in ipairs(objects) do
		o:draw()
	end
    player:draw()
	flame:draw()
    

	if #points >= 1 then
		points[#points+1] = vector(love.mouse.getPosition())
		love.graphics.setColor(100,100,255)
		love.graphics.line(unp(unpack(points)))
		points[#points] = nil
	end
	love.graphics.setColor(100,100,100)
	love.graphics.print(string.format("FPS: %d", love.timer.getFPS()), 10,10)

    love.graphics.draw(p, 0, 0)



end

function love.update(dt)
	flame:update(dt)
    player:update(dt)
    

    p:update(dt)
    
    p:setPosition(flamepos.x, flamepos.y)

end

function love.keyreleased(key)
	--if key == 'w' then debug.showWireframe = not debug.showWireframe end
	--if key == 'n' then debug.showNormals = not debug.showNormals end
	--if key == 'd' then debug.noDarkening = not debug.noDarkening end
	--if key == 'c' then objects = {} end
end



--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==
    -- Used to draw new polygons -- Turn into collision map editor? --
--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==--==

--[[
function love.mousereleased(x,y,btn)
	points[#points+1] = vector(x,y)
	if btn == 'r' and #points >= 3 then
		if not pcall(function()
				local convex = SplitConvex(points)
				objects = conc(objects, map(Polygon, convex))
			end) then
			pcall(function()
				for i=1,math.floor(#points/2) do
					points[i], points[#points+1-i] = points[#points+1-i], points[i]
				end
				local convex = SplitConvex(points)
				objects = conc(objects, map(Polygon, convex))
			end)
		end
		points= {}
	end
end]]--
