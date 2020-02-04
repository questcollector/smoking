package kr.co.miroirs.smoking.query;

public class Select {

    public final static String P015 = "SELECT id, geom, d_mo_p015 AS dayMorning, "
            + "d_no_p015 AS dayNoon, d_af_p015 AS dayAfternoon, d_ev_p015 AS dayEvening, "
            + "d_ni_p015 AS dayNight, e_mo_p015 AS endMorning, e_no_p015 AS endNoon, "
            + "e_af_p015 AS endAfternoon, e_ev_p015 AS endEvening, e_ni_p015 AS endNight, "
            + "count, count2 AS value, cluster_id "
            + "FROM \"P015\"";
    public final static String P100 = "SELECT id, geom, d_mo_p100 AS dayMorning, "
            + "d_no_p100 AS dayNoon, d_af_p100 AS dayAfternoon, d_ev_p100 AS dayEvening, "
            + "d_ni_p100 AS dayNight, e_mo_p100 AS endMorning, e_no_p100 AS endNoon, "
            + "e_af_p100 AS endAfternoon, e_ev_p100 AS endEvening, e_ni_p100 AS endNight, "
            + "count, count2 AS value, cluster_id "
            + "FROM \"P100\"";
}
