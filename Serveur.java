import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

//import service.AddImpl;
import javax.naming.Context;
import javax.naming.InitialContext;

import Airline.*;

public class Serveur {

	public static void main(String[] args) throws Exception {
		try {

			// ============================================================
			ORB orb = ORB.init(args, null); // Initialisation de ORB
			Context ctx = new InitialContext();
			// RootPOA est le POA manager principal
			POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));// Activation du POA manager
			poa.the_POAManager().activate();// Activation du POA;
			ImpClientService srv = new ImpClientService();
			ImpAdminService srv1 = new ImpAdminService();
			ImpAuthService srv2 = new ImpAuthService();

			ctx.rebind("AuthService", poa.servant_to_reference(srv));
			ctx.rebind("AdminService", poa.servant_to_reference(srv1));
			ctx.rebind("ClientService", poa.servant_to_reference(srv2));
			orb.run();
			System.out.println("Server Ready & Running...");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
