package kr.co.miroirs.smoking.dao;

public class Select {
    
    private Select() {
        throw new IllegalStateException("Query String class");
    }

    public static final String SELECT_P015 = "SELECT id, ST_AsText(geom) AS geom, d_mo_p015 AS dayMorning, "
            + "d_no_p015 AS dayNoon, d_af_p015 AS dayAfternoon, d_ev_p015 AS dayEvening, "
            + "d_ni_p015 AS dayNight, e_mo_p015 AS endMorning, e_no_p015 AS endNoon, "
            + "e_af_p015 AS endAfternoon, e_ev_p015 AS endEvening, e_ni_p015 AS endNight, "
            + "count, count2 AS value "
            + "FROM \"P015\"";
    public static final String SELECT_P100 = "SELECT id, ST_AsText(geom) AS geom, d_mo_p100 AS dayMorning, "
            + "d_no_p100 AS dayNoon, d_af_p100 AS dayAfternoon, d_ev_p100 AS dayEvening, "
            + "d_ni_p100 AS dayNight, e_mo_p100 AS endMorning, e_no_p100 AS endNoon, "
            + "e_af_p100 AS endAfternoon, e_ev_p100 AS endEvening, e_ni_p100 AS endNight, "
            + "count, count2 AS value "
            + "FROM \"P100\"";
    public static final String SELECT_BUILDING_CLUSTER = "SELECT id, ST_AsText(the_geom) AS the_geom, \"EQB_MAN_SN\", \"OPERT_DE\", "
            + "\"SIG_CD\" FROM \"건물군\"";
    
    public static final String SELECT_FILE_INFO = "SELECT id, file_name, file_path, file_type, "
            + "delete_flag, create_date, modify_date "
            + "FROM file_info "
            + "WHERE id = :fileId";
    
}
