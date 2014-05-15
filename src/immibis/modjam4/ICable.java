package immibis.modjam4;

public interface ICable {
	public void onNeighbourCableUnload(int dir, ICable obj, int x, int y, int z);
	public void propagateNetwork(CableNetwork newNetwork);
}
