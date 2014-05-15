package immibis.modjam4;

public class ProxyClient extends ProxyBase {
	@Override
	public void init() {
		Modjam4Mod.clientInit();
	}
}
