require("AnAL")

Player = Class{name="Player", function(self, x, y)
	self.x = x or 0
	self.y = y or 0
end}

function Player:load()
	local img  = love.graphics.newImage("assets/spritesheet.png")
	anim = newAnimation(img, 256, 256, 0.15, 0)
end

function Player:draw()
	love.graphics.push()
	love.graphics.setColor(255,255,255)
	love.graphics.scale(0.4, 0.4)
	anim:draw(self.x, self.y)
	love.graphics.pop()
end

function Player:update(dt)
	anim:update(dt)  

	if love.keyboard.isDown('a') then
		self.x = self.x - 5
	elseif love.keyboard.isDown('d') then
		self.x = self.x + 5
	end
	if love.keyboard.isDown('w') then
		self.y = self.y - 5
	elseif love.keyboard.isDown('s') then
		self.y = self.y + 5
	end

	playerpos = vector(self.x,self.y)
end