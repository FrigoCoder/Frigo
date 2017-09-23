
package frigo.math.crack;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import frigo.math.integer.MathInt;

public class Whatever3 {

    public static class Descriptor {

        public long s1;
        public long s2;
        public long t;
        public long n;

        public Descriptor (long s1, long s2, long t, long n) {
            this.s1 = s1;
            this.s2 = s2;
            this.t = t;
            this.n = n;
        }

        @Override
        public boolean equals (Object obj) {
            if( getClass() != obj.getClass() ){
                return false;
            }
            Descriptor o = (Descriptor) obj;
            return new EqualsBuilder().append(s1, o.s1).append(s2, o.s2).append(t, o.t).append(n, o.n).isEquals();
        }

        @Override
        public int hashCode () {
            return new HashCodeBuilder().append(s1).append(s2).append(t).append(n).toHashCode();
        }

        @Override
        public String toString () {
            return "[s1=" + s1 + ",s2=" + s2 + ",t=" + t + "]";
        }

    }

    public static void main (String[] args) {
        // long n = 11 * 17;
        // long n = 23 * 103;
        // long n = 11 * 17;
        // long n = 13 * 103;
        // long n = 10142789312725007L;
        long n = 47 * 113;

        long s = MathInt.sqrt(n);
        long t = n - s * s;

        Set<Descriptor> descriptors = new HashSet<>();
        descriptors.add(new Descriptor(s, s, t, n));
        print(descriptors);

        while( !finished(descriptors) ){
            descriptors = next(descriptors);
            print(descriptors);
        }
    }

    private static void print (Set<Descriptor> descriptors) {
        System.out.println(descriptors);
    }

    private static Set<Descriptor> next (Set<Descriptor> descriptors) {
        Set<Descriptor> result = new HashSet<>();
        for( Descriptor descriptor : descriptors ){
            long s1 = descriptor.s1;
            long s2 = descriptor.s2;
            long t = descriptor.t;
            long t2 = descriptor.t * 2;
            long n = descriptor.n;
            long n2 = descriptor.n % t2;

            if( (s1 + t) * (s2 + t) % t2 == n2 ){
                result.add(new Descriptor(s1 + t, s2 + t, t2, n));
                continue;
            }
            if( s1 * s2 % t2 == n2 ){
                result.add(new Descriptor(s1, s2, t2, n));
                continue;
            }
            if( (s1 + t) * s2 % t2 == n2 ){
                result.add(new Descriptor(s1 + t, s2, t2, n));
                continue;
            }
            if( s1 * (s2 + t) % t2 == n2 ){
                result.add(new Descriptor(s1, s2 + t, t2, n));
                continue;
            }
        }
        return result;
    }

    private static boolean finished (Set<Descriptor> descriptors) {
        if( descriptors.isEmpty() ){
            return true;
        }
        for( Descriptor descriptor : descriptors ){
            long n = descriptor.n;
            long gcd1 = MathInt.gcd(descriptor.s1, descriptor.n);
            if( gcd1 != 1 && gcd1 != n ){
                System.out.println("Factor found: " + gcd1);
                return true;
            }
            long gcd2 = MathInt.gcd(descriptor.s2, descriptor.n);
            if( gcd2 != 1 && gcd2 != n ){
                System.out.println("Factor found: " + gcd2);
                return true;
            }
        }
        return false;
    }

}
