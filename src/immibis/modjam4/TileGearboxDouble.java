package immibis.modjam4;

import immibis.modjam4.shaftnet.NetworkLink;
import immibis.modjam4.shaftnet.ShaftNetwork;
import immibis.modjam4.shaftnet.ShaftNode;
import immibis.modjam4.shaftnet.SpeedTorqueCurve;
import net.minecraft.tileentity.TileEntity;

public class TileGearboxDouble extends TileMachine {
	
	ShaftNode hsNode = new ShaftNode(this) {
		public SpeedTorqueCurve getSpeedTorqueCurve() {
			return new SpeedTorqueCurve() {
				@Override
				public long getTorqueAtSpeed(long hs_angvel) {
					return 0;
					//long ls_angvel = lsNode.getNetwork().angvel;
					//return -((hs_angvel / 2) - ls_angvel) * 2;
				}
			};
		}
	};
	ShaftNode lsNode = new ShaftNode(this) {
		public SpeedTorqueCurve getSpeedTorqueCurve() {
			return new SpeedTorqueCurve() {
				@Override
				public long getTorqueAtSpeed(long ls_angvel) {
					return 0;
					//long hs_angvel = hsNode.getNetwork().angvel;
					//return -((ls_angvel * 2) - hs_angvel) * 2;
				}
			};
		}
	};
	
	NetworkLink networkLink;
	
	
	ShaftNetwork lsNetwork = lsNode.getNetwork();
	ShaftNetwork hsNetwork = hsNode.getNetwork();
	
	private boolean firstTick = true;
	public void updateEntity() {
		if(firstTick) {
			firstTick = false;
			hsNode.setSideMask(1 << getBlockMetadata());
			lsNode.setSideMask(1 << (getBlockMetadata() ^ 1));
			updateNeighbourConnections();
		}
		lsNode.tick();
		hsNode.tick();
		
		ShaftNetwork lsNetwork_new = lsNode.getNetwork();
		ShaftNetwork hsNetwork_new = hsNode.getNetwork();
		
		if(networkLink == null || lsNetwork_new != lsNetwork || hsNetwork_new != hsNetwork) {
			lsNetwork = lsNetwork_new;
			hsNetwork = hsNetwork_new;
			
			if(networkLink != null) {
				//System.out.println("unlinking old gearbox link");
				networkLink.unlink();
			}
			networkLink = new NetworkLink(lsNetwork_new, hsNetwork_new, 2);
			//System.out.println("linking new gearbox link");
			networkLink.link();
		}
	}
	
	@Override
	protected void updateNeighbourConnections() {
		lsNode.updateNeighbours();
		hsNode.updateNeighbours();
		
		
		
		if(!worldObj.isRemote)
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
	
	@Override
	public ShaftNode getShaftNode(int side) {
		int meta = getBlockMetadata();
		if(side == meta)
			return hsNode;
		if(side == (meta ^ 1))
			return lsNode;
		return null;
	}
	
	/*
	public static final double MOMENT_OF_INERTIA = TileShaft.MOMENT_OF_INERTIA * 30;
	
	// angle/angvel of low-speed side
	public int angle;
	public int angvel;
	
	@Override
	public void updateEntity() {
		int meta = getBlockMetadata();
		IShaft hsShaft = getConnectedShaft(meta);
		IShaft lsShaft = getConnectedShaft(meta^1);
		if(hsShaft == null || lsShaft == null)
			return;
		
		int hs_angvel = hsShaft.getAngVel(meta^1);
		int hs_angle = hsShaft.getAngle(meta^1);
		double hs_moi = hsShaft.getMomentOfInertia(meta^1);
		int ls_angvel = lsShaft.getAngVel(meta);
		int ls_angle = lsShaft.getAngle(meta);
		double ls_moi = lsShaft.getMomentOfInertia(meta);
		
		double totEnergy = 0.5 * (ls_moi*ls_angvel*ls_angvel + hs_moi*hs_angvel*hs_angvel + MOMENT_OF_INERTIA*angvel*angvel);
		
		// find angvel such that
		//   totEnergy = 0.5 * (ls_moi + MOMENT_OF_INERTIA + hs_moi*4)*angvel*angvel
		angvel = (int)Math.sqrt(totEnergy * 2 / (ls_moi + MOMENT_OF_INERTIA + hs_moi*4));
		
		//System.out.println(totEnergy+" "+0.5 * (ls_moi*angvel*angvel + hs_moi*angvel*angvel*4 + MOMENT_OF_INERTIA*angvel*angvel));
		
		angle += angvel;
	}
	

	@Override
	public int getAngle(int side) {
		if(side == getBlockMetadata())
			return angle * 2;
		return angle;
	}

	@Override
	public int getAngVel(int side) {
		if(side == getBlockMetadata())
			return angvel * 2;
		return angvel;
	}

	@Override
	public boolean doesShaftConnect(int side) {
		return (side & 6) == (getBlockMetadata() & 6);
	}

	@Override
	public double getMomentOfInertia(int side) {
		// TODO Auto-generated method stub
		return 0;
	}*/

}
