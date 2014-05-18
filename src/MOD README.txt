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
If you place a block overlapping a watermill, it will break into an item.

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
Fans always have a range of 8 blocks.

A fan connected directly to a watermill will not push anything very quickly,
except on an ice floor.
To fix this, you can use a speed-doubling gearbox.
In a 3x3 crafting grid, place 6 cobblestone along the top and bottom rows, and
3 stone gears in the middle row.
A speed-doubling gearbox connects two axles, and forces one to spin
at twice the speed of the other. Of course, this takes extra power.
When placed, you will notice an arrow on the side pointing towards you.
The arrow points to the high-speed side. An axle connected to this side
will spin at twice the speed of an axle connected to the opposite side, no
matter what (exception: bugs). The other sides do nothing.

They can be chained together, of course. For pushing items a short distance,
a single watermill connected to a fan through a single s.d. gearbox is
sufficient. For longer distances, or if you're impatient, you will need
to add more gearboxes, and more watermills to produce the required power.

The second use for axle power is milling wheat into flour. There are no other
milling recipes at this time.
A millstone is crafted from a wooden axle surrounded by 8 smooth stone.
Once it is turning, right click it with a piece of wheat to start milling.
If your millstone is directly connected to a single watermill, it will slow down
dramatically until it is barely turning at all.
If your millstone is running at twice the watermill speed, it will actually go
faster even though it takes more power to run at the higher speed. Lower speeds
are not always more efficient!
Wheat can be inserted with hoppers or pipes (from other mods). Flour will
always pop out the top. Flour can be smelted into bread.

The third use for axle power is to boost minecarts.

