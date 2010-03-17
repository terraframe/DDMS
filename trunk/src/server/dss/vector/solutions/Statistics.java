package dss.vector.solutions;

import java.util.Arrays;

public class Statistics {
	
	private static final double BINOMIAL_LIMIT = 1E+30;
	private static final double BINOMIAL_TOLERANCE = 0.000001;

	public double quartile(double[] data, int quart){
		int n = data.length;
		double[] a = new double[n];
		System.arraycopy(data, 0, a, 0, n);
		Arrays.sort(a);

		switch(quart) {
			case 0:
				return a[0];
			case 1:
			case 2:
			case 3:
				int k = 1 + ( (quart * (n - 1)) / 4);
				double f = 0;
				if ((quart * (n - 1)) % 4 != 0) {
					f = 1.0 + ( ((double) quart * ((double) n - 1.0)) / 4.0) - (double) k;
				}
				
				return a[k-1] + (f * (a[k] - a[k-1]));
			case 4:
				return a[n-1];
			default:
				throw new UnsupportedOperationException();
		}
	}

	private double binomHigh(double vx, double vN, double pp) {
		if (vx == vN) {
			return 1.0;
		}
		double p = (vx == 0.0 ? 2.0 * pp : pp);

		double vP = vx / vN;
		double v = (1.0 + vP) / 2.0;
		double vsL = vP;
		double vsH = 1.0;
		
		while ((vsH - vsL) > BINOMIAL_TOLERANCE) {
		    if (this.binomP(vN, v, 0, vx) < p) {
		        vsH = v;
		        v = (vsL + v) / 2.0;
		    } else {
		        vsL = v;
		        v = (v + vsH) / 2.0;
		    }
		}
		
		return v;
	}

	private double binomP(double n, double p, double x1, double x2) {
		double q = p / (1.0 - p);
		double k = 0.0;
		double v = 1.0;
		double s = 0.0;
		double tot = 0.0;
		
		while (k <= n) {
		    tot = tot + v;
		    if ((k >= x1) && (k <= x2)) {
		        s = s + v;
		    }
		    if (tot > BINOMIAL_LIMIT) {
		        s = s / BINOMIAL_LIMIT;
		        tot = tot / BINOMIAL_LIMIT;
		        v = v / BINOMIAL_LIMIT;
		    }
		    k = k + 1.0;
		    v = v * q * (n + 1.0 - k) / k;
		}
		return s / tot;
	}

	public double binomial(double vx, double vN, double pp) {
		if (vx == 0.0d) {
			return 0.0d;
		}
		return this.binomHigh(vx, vN, pp) * vN;
	}

}
