package immibis.modjam4;

public interface IShaft {
	public int getAngle(int side);
	public int getAngVel(int side);
	public boolean doesShaftConnect(int side);
}