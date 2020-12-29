import java.lang.Math;


public class tinh_toan_saiso_chuan {
	public static void main(String[] args) {
		double dr=0.5;double P[]= {250,500,100};
		double phi=60*Math.PI/180;
	   
		for(int i=1;i<=7;i++) {
			
			double Pdr[]=Pdr(P, dr, i);
			double Q[]=find_Q(Pdr, phi);
			double Q_chuan[]=find_Q(P, phi);
			int k=3;
			System.out.println(Q[k]-Q_chuan[k]);
			
			/*
			double Q_chuan[]=find_Q(P, phi);
			double delta_ad[]=find_delta_ad(Pdr, phi, P);
			 double delta_q[]=find_deta_q(Pdr, phi, P);
		
			double R=R(delta_ad, delta_q, Pdr, phi);
			if(R<dr) {
				System.out.println(delta_ad[2]);
			//	System.out.println("delta_a2="+delta_ad[1]);
			//	System.out.println("delta_d14="+delta_ad[2]);	
			 //   System.out.println(R);
			}
		int k=0;
			while(R>dr) {
				int m=100;k++;
				delta_ad[0]=delta_ad[0]-2*delta_ad[0]/m;
				delta_ad[1]=delta_ad[1]-2*delta_ad[1]/m;
				delta_ad[2]=delta_ad[2]-2*delta_ad[2]/m;
				
				R=R(delta_ad, delta_q, Pdr, phi);
				if(R<dr) {
				 System.out.println(delta_ad[2]);
				//	System.out.println("delta_a2="+delta_ad[1]);
					//System.out.println("delta_d14="+delta_ad[2]);	
				//   System.out.println(R);
				    break;
				};
			}
			
			/*
			System.out.println("px="+Pdr[0]);
			System.out.println("py="+Pdr[1]);
			System.out.println("pz="+Pdr[2]);
			
			System.out.println("q1="+Q[0]);
			System.out.println("q2="+Q[1]);
			System.out.println("q3="+Q[2]);
			System.out.println("q4="+Q[3]);
				
			System.out.println("delta_a1="+delta_ad[0]);
			System.out.println("delta_a2="+delta_ad[1]);
			System.out.println("delta_d14="+delta_ad[2]);	
			
			
			System.out.println("delta_q1="+delta_q[0]);
			System.out.println("delta_q2="+delta_q[1]);
			System.out.println("delta_q3="+delta_q[2]);
			System.out.println("delta_q4="+delta_q[3]);
			
			System.out.println("================");
			*/
			
			/*
			int k=3;
			System.out.println(Q[k]-Q_chuan[k]);
			
			//System.out.println(Q[k]);
			*/
			}
		
	}

// tinh toan toa do cuoi
	public static double[] find_P(double ad[], double q[]) {
		double a1 = ad[0];
		double q1 = q[0];
		double a2 = ad[1];
		double q2 = q[1];
		double d14 = ad[2];
		double q3 = q[2];
		double q4 = q[3];
		double px = a2 * Math.cos(q1 + q2) + a1 * Math.cos(q1);
		double py = a2 * Math.sin(q1 + q2) + a1 * Math.sin(q1);
		double pz = d14 - q3;
		double phi=q1+q2-q4;
		double P[] = { px, py, pz ,phi};
		return P;
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

// tinh bien khop q chuan
public static double[] find_Q(double P[],double phi) {
	double a1=350;double a2=250;double d14=167;
	double px=P[0];double py=P[1];double pz=P[2];
	
	double cq2 = (Math.pow(px, 2) + Math.pow(py, 2) - Math.pow(a1, 2) - Math.pow(a2, 2)) / (2 * a1 * a2);
	double sq2 = Math.sqrt(1 - Math.pow(cq2, 2));
	double q2 = Math.atan2(sq2, cq2);

	double cq1 = (a1 * px + a2 * (px * cq2 + py * sq2)) / (Math.pow(py, 2) + Math.pow(px, 2));
	double sq1 = (a1 * py + a2 * (py * cq2 - px * sq2)) / (Math.pow(py, 2) + Math.pow(px, 2));
	double q1 = Math.atan2(sq1, cq1);

	double q3 = d14 - pz;
	double q4 = -Math.atan2(Math.sin(-phi),Math.cos(phi) ) + q1 + q2;
	double[] Q = { q1, q2, q3, q4 };
	return Q;
}
//giu nguyen a d tinh sai so q
public static double[] find_deta_q(double Pdr[],double phi,double P[]) {
	double Q[]=find_Q(Pdr, phi);
	double Q1[]=find_Q(P, phi);
	
	double delta_q1=Q[0]-Q1[0];
	double delta_q2=Q[1]-Q1[1];
	double delta_q3=Q[2]-Q1[2];
	double delta_q4=Q[3]-Q1[3];
	
	double delta_q[]= {delta_q1,delta_q2,delta_q3,delta_q4};
	return delta_q;
}

// giu nguyen q tinh sai so a d
public static double [] find_delta_ad(double Pdr[],double phi,double P[]) {
	double Q[]=find_Q(P, phi);
	double q1=Q[0];double q2=Q[1];double q3=Q[2];double q4=Q[3];
	double px=Pdr[0];double py=Pdr[1];double pz=Pdr[2];
	
	double c12=Math.cos(q1+q2); double s1=Math.sin(q1); double s2=Math.sin(q2);
	double s12=Math.sin(q1+q2); double c1=Math.cos(q1); double c2=Math.cos(q2);
	
	double a1=(-c12*py+px*s12)/(c1*s12-c12*s1);
	double a2=(c1*py-px*s1)/(c1*s12-c12*s1);
	double d14=pz+q3;
	
	double delta_a1=a1-350;
	double delta_a2=a2-250;
	double delta_d14=d14-167;
	
	double delta_ad[]= {delta_a1,delta_a2,delta_d14};
	return delta_ad;
		
}
public static double R(double []delta_ad,double delta_q[],double P[],double phi){
	double a1=350;double a2=250;double d14=167;
	double a11=a1+delta_ad[0];
	double a22=a2+delta_ad[1];
	double d144=d14+delta_ad[2];
	double AD[]= {a11,a22,d144};
	
	double q[]=find_Q(P, phi);
	double qq1=q[0]+delta_q[0];
	double qq2=q[1]+delta_q[1];
	double qq3=q[2]+delta_q[2];
	double qq4=q[3]+delta_q[3];
	double QQ[]= {qq1,qq2,qq3,qq4};
	double P1[]=find_P(AD, QQ);

	double R=Math.sqrt(Math.pow(P1[0]-P[0], 2)+Math.pow(P1[1]-P[1], 2)+Math.pow(P1[2]-P[2], 2));
	
	return R;
}
}
