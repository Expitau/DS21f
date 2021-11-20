public class Time implements Comparable<Time>{
    public Integer d, h, m;

    public Time(int d, int h, int m){
        this.d = d;
        this.h = h;
        this.m = m;
    }

    public Time(Time t){
        this.d = t.d;
        this.h = t.h;
        this.m = t.m;
    }
    
    private void uniformize(){
        h += m/60;
        m %= 60;
        d += h/24;
        h %= 24;
    }

    public static Time parseTime(String str){
        if(str.length() != 4) return null;
        Integer timeString = Integer.parseInt(str);
        return new Time(0, timeString/100, timeString%100);
    }

    @Override
    public int compareTo(Time t){
        if(t.d == -1 && t.h == -1 && t.m == -1) return -1;
        if(d == -1 && h == -1 && m == -1) return 1;
        if(d != t.d) return Integer.compare(d, t.d);
        if(h != t.h) return Integer.compare(h, t.h);
        return Integer.compare(m, t.m);
    }

    public void add(Time t){
        d += t.d;
        h += t.h;
        m += t.m;
        uniformize();
    }

    public void addD(Integer d){
        this.d += d;
        uniformize();
    }

    public static Integer getDelayDay(Time t1, Time t2){
        t2.uniformize();

        Integer nowD = t2.d;
        t2.d = Math.max(t2.d, t1.d);
        if(t2.compareTo(t1) < 0) t2.d++;
        
        return t2.d - nowD;
    }
    
    public String toString(){
        return String.format("%02d%02d", h, m);
    }
}
