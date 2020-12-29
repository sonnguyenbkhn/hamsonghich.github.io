import java.lang.Math;
public class saiso_q {

	public static void main(String[] args) {

		double a[] = { 350, 250 };
		double d14 = 167;
		double dr = 0.5;
		double P[] = { 350, 200, 100 }; // set up diem chuan
		double phi = 0* Math.PI / 180;
		double Q[] = Q(P, phi); // bien khop chuan 
		for (int i = 1; i <= 7; i++) {
			double Pdr[] = Pdr(P, dr, i);
			double Q_saiso[] = Q_saiso(Pdr, Q, phi);

			double delta_q1 = Q[0] - Q_saiso[0];
			double delta_q2 = Q[1] - Q_saiso[1];
			double delta_q3 = Q[2] - Q_saiso[2];
			double delta_q4 = Q[3] - Q_saiso[3];

			System.out.println("px= " + Pdr[0]);
			System.out.println("py= " + Pdr[1]);
			System.out.println("pz= " + Pdr[2]);

			System.out.println("q1= "+Q[0]);
			System.out.println("q2= "+Q[1]);
			System.out.println("q3= "+Q[2]);
			System.out.println("q4= "+Q[3]);
			
			System.out.println("delta_q1= " + delta_q1);
			System.out.println("delta_q2= " + delta_q2);
			System.out.println("delta_q3= " + delta_q3);
			System.out.println("delta_q4= " + delta_q4);
			
			System.out.println("------------------------------1");
		}
	}

	public static double[] Q(double[] P, double phi) {
		double px = P[0];
		double py = P[1];
		double pz = P[2];

		double a1 = 350;
		double a2 = 250;
		double d1 = 250;
		double d4 = 83;
		double cq2 = (Math.pow(px, 2) + Math.pow(py, 2) - Math.pow(a1, 2) - Math.pow(a2, 2)) / (2 * a1 * a2);
		double sq2 = Math.sqrt(1 - Math.pow(cq2, 2));
		double q2 = Math.atan2(sq2, cq2);

		double cq1 = (a1 * px + a2 * (px * cq2 + py * sq2)) / (Math.pow(py, 2) + Math.pow(px, 2));
		double sq1 = (a1 * py + a2 * (py * cq2 - px * sq2)) / (Math.pow(py, 2) + Math.pow(px, 2));
		double q1 = Math.atan2(sq1, cq1);

		double q3 = -d4 + d1 - pz;
		double q4 = -Math.atan2(Math.sin(-phi),Math.cos(phi) ) + q1 + q2;
		double[] Q = { q1, q2, q3, q4 };
		return Q;
	}

	public static double[] Pdr(double[] P, double dr, int chose) {
		double px = P[0];
		double py = P[1];
		double pz = P[2];
		double[] Pdr = { px, py, pz };
		switch (chose) {

		case 2:
			Pdr = new double[] { px + dr, py, pz };
			break;
		case 3:
			Pdr = new double[] { px, py + dr, pz };
			break;
		case 4:
			Pdr = new double[] { px - dr, py, pz };
			break;
		case 5:
			Pdr = new double[] { px, py - dr, pz };
			break;
		case 6:
			Pdr = new double[] { px, py, pz - dr };
			break;
		case 7:
			Pdr = new double[] { px, py, pz + dr };
			break;
		default:
			break;
		}
		return Pdr;
	}

	public static double[] Q_saiso(double Pdr[],double Q[],double phi){
		    double a1=350;double a2=250;
		    double d14=167;
		    double px=Pdr[0];double py=Pdr[1];double pz=Pdr[2];
		    
		    double cosq2=(px*px+py*py-a1*a1-a2*a2)/(2*a1*a2);
		    double sinq2=Math.sqrt(1-cosq2*cosq2);
		    double q2=Math.atan2(sinq2,cosq2);

		    double cosq1=(a1*px+a2*(px*cosq2+py*sinq2))/(px*px+py*py);
		    double sinq1=(a1*py+a2*(py*cosq2-px*sinq2))/(px*px+py*py);
		    double q1=Math.atan2(sinq1,cosq1);

		    double q3=d14-pz;
		    double q4=-phi+q1+q2;
		    double []Q1={q1,q2,q3,q4};
		    return Q1;
		   
		}

}
