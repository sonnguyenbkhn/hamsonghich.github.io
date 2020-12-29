

// code nay su dung khi ta da biet cac sai so cua khop 1 2 ung voi so xung 350.000 xung va 80.000 xung 300.000 xung
public class saiso_final {
	public static void main(String[] args) {
		double P[] = 	P_VALUE(3);
		double phi = 30 * Math.PI / 180;
		double delta_q1 = Math.PI / 3500;
		double delta_q2 = Math.PI / 2500;
		double delta_q4 = Math.PI / 2000;
		/*
		 * double QQ[]=find_Q(P, phi);
		 * System.out.println(Math.round(QQ[0]/delta_q1)*delta_q1);
		 * System.out.println(Math.round(QQ[1]/delta_q2)*delta_q2);
		 * System.out.println(Math.round(QQ[3]/delta_q4)*delta_q4);
		 */
		for (int i = 1; i <= 7; i++) {
			double Pdr[] = Pdr(P, phi, i);
			double Q[] = find_Q(P, phi);

			double px = Pdr[0];
			double py = Pdr[1];
			double pz = Pdr[2];
			double q1_ = Math.round(Q[0] / delta_q1) * delta_q1;
			double q2_ = Math.round(Q[1] / delta_q2) * delta_q2;
			double q4_ = Math.round(Q[3] / delta_q4) * delta_q4;

			double a1_ = (-cos(q1_ + q2_) * py + px * sin(q1_ + q2_))/ (cos(q1_) * sin(q1_ + q2_) - cos(q1_ + q2_) * sin(q1_));
			double a2_ = (cos(q1_) * py - px * sin(q1_)) / (cos(q1_) * sin(q1_ + q2_) - cos(q1_ + q2_) * sin(q1_));

			System.out.println(q2_);

			// System.out.println(Math.round(Q[0]/delta_q1)*delta_q1);
			// System.out.println(Math.round(Q[1]/delta_q2)*delta_q2);
			// System.out.println(Math.round(Q[3]/delta_q4)*delta_q4);
		}
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

	public static double[] P_VALUE(int chose) {
		double P_VALUE[] = { 0, 0, 0 };
		switch (chose) {
		case 1:
			P_VALUE = new double[] { 350, 200, 100 };
			break;
		case 2:
			P_VALUE = new double[] { 300, 250, 150 };
			break;
		case 3:
			P_VALUE = new double[] { 200, 100, 165 };
			break;
		case 4:
			P_VALUE = new double[] { 100, 100, 145 };
			break;
		case 5:
			P_VALUE = new double[] { 550, 100, 150 };
			break;
		case 6:
			P_VALUE = new double[] { 250, 500, 100 };
			break;
		}
		return P_VALUE;
	}

// tinh bien khop q chuan
	public static double[] find_Q(double P[], double phi) {
		double a1 = 350;
		double a2 = 250;
		double d14 = 167;
		double px = P[0];
		double py = P[1];
		double pz = P[2];

		double cq2 = (Math.pow(px, 2) + Math.pow(py, 2) - Math.pow(a1, 2) - Math.pow(a2, 2)) / (2 * a1 * a2);
		double sq2 = Math.sqrt(1 - Math.pow(cq2, 2));
		double q2 = Math.atan2(sq2, cq2);

		double cq1 = (a1 * px + a2 * (px * cq2 + py * sq2)) / (Math.pow(py, 2) + Math.pow(px, 2));
		double sq1 = (a1 * py + a2 * (py * cq2 - px * sq2)) / (Math.pow(py, 2) + Math.pow(px, 2));
		double q1 = Math.atan2(sq1, cq1);

		double q3 = d14 - pz;
		double q4 = -Math.atan2(Math.sin(-phi), Math.cos(phi)) + q1 + q2;
		double[] Q = { q1, q2, q3, q4 };
		return Q;
	}

	public static double cos(double a) {
		return Math.cos(a);
	}

	public static double sin(double a) {
		return Math.sin(a);
	}
}
