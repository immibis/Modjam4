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


Another way to turn axles is with windmills.
A sail is crafted with 3 sticks on the left side of a crafting table, and wool
in the other 6 slots. 4 sails around an axle (in a + shape) makes a windmill.
Windmills are 5x5, similar to watermills, but all 25 blocks are solid.
For now, they generate power from nothing, because I didn't have time to
implement anything else (like checking for open sky, or obstructions).

Windmills turn at the same speed as watermills, but are only 1/4 as powerful
(at maximum).




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



The third current use for mechanical power is to boost minecarts.
A cart booster is crafted like so:

  cobble  iron  cobble
   axle   gear   axle 
  cobble cobble cobble

If turning, it will boost any minecarts that roll over it (place rails on top).
It will boost minecarts going in any direction and will never change the
direction of minecarts. It will not affect minecarts that are stopped.
The speed of the minecart after the boost depends on the speed the booster is
rotating at. At 360 degrees per second (8 times the full speed of a watermill)
it will be the maximum speed allowed by vanilla code, which can propel
an empty minecart up an 8 block slope, or an occupied minecart up a 24 block
slope.


The last current use for mechanical power is...

...

...

GIANT SPINNY DEATH BLADES! Yes, they are actually called that in-game.

An iron blade is crafted from 5 iron like this:
    iron  iron nothing
  nothing iron  iron
  nothing iron nothing

8 iron blades surrounding a wooden axle makes a Giant Spinny Death Blade.
Giant Spinny Death Blades are 7x7 and only the centre block is solid.
They work almost the way you'd expect, although vertical ones have not been fully tested.
Any entity walking into their range will periodically take damage. Both the
amount and the frequency of damage depend on the speed. At 720 degrees/second,
they will instantly kill most things in range - lower speeds of course do
proportionally less damage.
Note: Entities will not take damage when the blade hits them, but they will
take damage about 4 times per revolution. I didn't have time to make it fancier.
These should be quite useful for mob traps, especially for slimes.
