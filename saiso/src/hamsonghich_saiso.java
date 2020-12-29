
public class hamsonghich_saiso {
	public static void main(String[] args) {
		double P[] = Pi(3); // CHON DIEM P
		double phi = 60 * Math.PI / 180;
		double dr = 0.5;

		// TIM SAI SO Q CHUAN THEO ENCODER
		double delta_q1 = 2 * Math.PI / 6500;
		double delta_q2 = 2 * Math.PI / 6500;
		double delta_q4 = 2 * Math.PI / 3500;

		double Q[] = Q(P, phi);

		double q1 = Math.round(Q[0] / delta_q1) * delta_q1;
		double q2 = Math.round(Q[1] / delta_q2) * delta_q2;
		double q4 = Math.round(Q[3] / delta_q4) * delta_q4;
		double q3 = Q[2];
		double Q_encoder[] = { q1, q2, q3, q4 };

		double P_s[] = P(350, 250, 167, Q_encoder);

		for (int i = 1; i <= 7; i++) {
			double Pdr[] = Pdr(P, dr, i); // sai lech diem ban kinh dr
			double Q_[] = Q(Pdr, phi);
			double AD[] = AD(Q_encoder, Pdr);

			double delta_a1 = AD[0] - 350;
			double delta_a2 = AD[1] - 250;
			double delta_d14 = AD[2] - 167;

			double px = P(AD[0], AD[1], AD[2], Q_encoder)[0];
			double py = P(AD[0], AD[1], AD[2], Q_encoder)[1];
			double pz = P(AD[0], AD[1], AD[2], Q_encoder)[2];

			double ri = Math.sqrt((Math.pow(px - P_s[0], 2)) + Math.pow(py - P_s[1], 2) + Math.pow(pz - P_s[2], 2));
			 
			if (ri <= 0.5) {
				System.out.println(ri + " ko can while");
				// System.out.println(delta_a1);
				// System.out.println(delta_a2);
				// System.out.println(delta_d14);
				// System.out.println("");
			} 
				while (ri > 0.5) {
					double m = 10;
					if (delta_a1 > 0) {
						delta_a1 = delta_a1 - 2 * delta_a1 / m;
					} else
						delta_a1 = delta_a1 - 2 * delta_a1 / m;
					if (delta_a2 > 0) {
						delta_a2 = delta_a2 - 2 * delta_a2 / m;
					} else
						delta_a2 = delta_a2 - 2 * delta_a2 / m;
					if (delta_d14 > 0) {
						delta_d14 = delta_d14 - 2 * delta_d14 / m;
					} else
						delta_d14 = delta_d14 - 2 * delta_d14 / m;

					px = P(350 + delta_a1, 250 + delta_a2, 167 + delta_d14, Q_encoder)[0];
					py = P(350 + delta_a1, 250 + delta_a2, 167 + delta_d14, Q_encoder)[1];
					pz = P(350 + delta_a1, 250 + delta_a2, 167 + delta_d14, Q_encoder)[2];

					ri = Math.sqrt((Math.pow(px - P_s[0], 2)) + Math.pow(py - P_s[1], 2) + Math.pow(pz - P_s[2], 2));
					//System.out.println(ri+ " while");
					if (ri <= 0.5)
					 System.out.println(ri + " co can while");
					// System.out.println(delta_a1);
					// System.out.println(delta_a2);
					// System.out.println(delta_d14);
					// System.out.println("");
					break;
				}
			
		}

	}

// lua chon diem thao tac 
	public static double[] Pi(int chose) {
		double Pi[] = { 0, 0, 0 };
		switch (chose) {
		case 1: {
			Pi = new double[] { 350, 200, 100 };
			break;
		}
		case 2: {
			Pi = new double[] { 300, 250, 150 };
			break;
		}
		case 3: {
			Pi = new double[] { 200, 100, 165 };
			break;
		}
		case 4: {
			Pi = new double[] { 100, 100, 145 };
			break;
		}
		case 5: {
			Pi = new double[] { 550, 100, 150 };
			break;
		}
		case 6: {
			Pi = new double[] { 250, 500, 100 };
			break;
		}
		}
		return Pi;
	}

// sai so tung diem dua theo dr
	public static double[] Pdr(double Pi[], double dr, int chose) {
		double px = Pi[0];
		double py = Pi[1];
		double pz = Pi[2];
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

// tinh bien khop q co dinh ad
	public static double[] Q(double P[], double phi) {
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

// tinh sai so a d khi co dinh q
	public static double[] AD(double Q[], double P[]) {
		double px = P[0];
		double py = P[1];
		double pz = P[2];
		double q1 = Q[0];
		double q2 = Q[1];
		double q3 = Q[2];
		double q4 = Q[3];
		double c12 = Math.cos(q1 + q2);
		double s12 = Math.sin(q1 + q2);
		double c1 = Math.cos(q1);
		double s1 = Math.sin(q1);

		double a1 = (-c12 * py + px * s12) / (c1 * s12 - c12 * s1);
		double a2 = (c1 * py - px * s1) / (c1 * s12 - c12 * s1);
		double d14 = pz + q3;
		double AD[] = { a1, a2, d14 };
		return AD;
	}

// tao do diem thao tac cuoi
	public static double[] P(double a1, double a2, double d14, double Q[]) {
		double px = a2 * Math.cos(Q[0] + Q[1]) + a1 * Math.cos(Q[0]);
		double py = a2 * Math.sin(Q[0] + Q[1]) + a1 * Math.sin(Q[0]);
		double pz = -Q[2] + d14;
		double P[] = { px, py, pz };
		return P;
	}
}
