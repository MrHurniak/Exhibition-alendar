package ua.expo.model.dao.impl;

interface SQLQueries {
    public static final String HALL_GET_BY_ID = "SELECT * FROM ExpositionProject.exhibitionHalls where id = ?;";
    public static final String HALL_UPDATE = "update ExpositionProject.exhibitionHalls set name=?, information=?, state=? where id=?;";
    public static final String HALL_SAVE_DELETE = "update ExpositionProject.exhibitionHalls set state='DELETED' where id=?;";
    public static final String HALL_DELETE = "delete from ExpositionProject.exhibitionHalls where id = ?; ";
    public static final String
    public static final String
    public static final String EXPOSITION_SAVE_DELETE = "update ExpositionProject.expositions  set state='DELETED' where hall_id = ?;";
    public static final String
    public static final String
    public static final String
}
