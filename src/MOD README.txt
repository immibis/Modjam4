This mod adds a few low-tech machines. I didn't have time to add more
machines (I didn't even think of this mod idea until about halfway through
Modjam) or to fix all the bugs (or not write them to start with).

And there are a lot of bugs. If everything starts spinning at insane speeds,
exit and re-load the world. Speeds aren't saved because a) no time and b) then
you'd have no way to stop the bug that makes everything spin at insane speeds
sometimes.
Also everything is done client-side and server-side, with minimal syncing, so
desyncs might happen easily.




The first things you need to craft are wooden axles.
Put 6 sticks in a 3x3 crafting grid - three on the left and three on the right.
Like a ladder without the centre stick. This should give you 6 wooden axles.
Make a stack or so of these.

Axles are placeable and are used to connect machines. They work how you'd expect.
Try placing some.

So how do you turn axles?
Craft this pattern:
 W 
WWW
 /
where / is a stick and W's are wooden planks.
This will give you a wooden paddle. Surround a wooden axle with 8 wooden paddles
to make a wooden watermill. This is a FIVE BY FIVE BLOCK BIG machine which must
be placed on a wall. The middle 3x3 blocks are solid. If there is water running
along the outer edge (overlapping the watermill), it will turn.
Watermills will always try to turn at the same speed, but they will produce more
torque if there is more water.
If you connect axles to a watermill, they will spin along with it.

Another important crafting item is the Stone Gear.
These are made by surrounding a wooden axle with four cobblestone blocks -
in a + shape.
If you craft a + shape of stone gears, with cobblestone in the corners:
  cobble  gear  cobble
   gear   gear   gear 
  cobble  gear  cobble
you will get a stone gearbox.
Axles connected to any side of a stone gearbox will rotate together.
Using stone gearboxes, you can connect things that are not in straight lines.
There is no penalty for using stone gearboxes - they don't have increased
friction, or a low max speed, or anything.

The first use for axle power is the fan.
A fan is crafted from a wooden axle surrounded by four iron ingots, in a + shape.
Fans can be connected to axles to blow entities away from them. They will
not affect players, but will affect mobs and dropped items.

A fan connected directly to a watermill will not push anything very quickly.
To fix this, you can use a speed-doubling gearbox.
In a 3x3 crafting grid, place 6 cobblestone along the top and bottom rows, and
3 stone gears in the middle row.