
public class test_q {
	public static void main(String[] args) {
		double  a1=350; double a2=250; double d14=167;
		
		double P[] = { 600, 0, 100 }; // set up diem chuan
		double phi = 60* Math.PI / 180;
		double Q[] = Q(P, phi); // bien khop chuan
		
		double delta_q1=0.000015;
		double delta_q2=0.0008;
		double delta_q4=0.00002;
		
		double q1=-delta_q1+Q[0];
		double q2=+delta_q2+Q[1];
		double q3=Q[2];
		double q4=-delta_q4+Q[3];
		
		double px=a2*Math.cos(q1+q2)+a1*Math.cos(q1);
		double py=a2*Math.sin(q1+q2)+a1*Math.sin(q1);
		
		double delta_phi=q1+q2;
		
		System.out.println("px="+(px-600));
		System.out.println("py="+py);
		System.out.println("delta_phi="+delta_phi);
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
		double q4 = -phi + q1 + q2;
		double[] Q = { q1, q2, q3, q4 };
		return Q;
	}
}
