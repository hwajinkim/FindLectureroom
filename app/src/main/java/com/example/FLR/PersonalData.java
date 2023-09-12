package com.example.FLR;

public class PersonalData {
    //학생필드
    private String student_Name;
    private String student_Snumber;
    private String student_Rnumber;
    private String student_Sub;
    private String student_Stime;
    private String student_Etime;
    private String student_Scode;
    private String student_Sname;
    private String student_Ssort;
    private String student_Sper;
    private String student_Scre;
    private String student_Sday;



    private String student_Syear;
    private String student_Sclass;
    private String student_Rnum;
    private String student_Pnum;
    //교수 필드
    private String professor_Number;
    private String professor_Pname;
    private String professor_Pposi;
    private String professor_Ptel1;
    private String professor_Ptel2;
    private String professor_Ptel3;
    private String professor_Pemail1;
    private String professor_Pemail2;
    private String professor_Proom;
    private String professor_Pstatus;

    //과사 필드
    private String department_Dname;
    private String department_Dtel1;
    private String department_Dtel2;
    private String department_Dtel3;
    private String department_Droomnum;
    private String department_Dstart;
    private String department_Dend;

    //예약 필드
    private String reservationPeopleNum;
    private String reservationRoomNum;
    private String reservationRentalStartTime;
    private String reservationRentalEndTime;
    private String reservationRentalDate;
    private String reservationPerposeOfUse;
    private String reservationBookStatus;
    private String reservationCancelReason;

    //강의실 필드
    private String lectureRoomCapacity;


    //학생 데이터(값)을 읽어오는 부분
    public String getStudent_Name()
    {
        return student_Name;
    }

    public String getStudent_Snumber() {

        return student_Snumber;
    }
    public String getStudent_Rnumber() {

        return student_Rnumber;
    }

    public String getStudent_Sub() {

        return student_Sub;
    }
    public String getStudent_Stime() {

        return student_Stime;
    }
    public String getStudent_Etime() {

        return student_Etime;
    }
    public String getStudent_Scode() {

        return student_Scode;
    }
    public String getStudent_Sname() {

        return student_Sname;
    }
    public String getStudent_Ssort() {

        return student_Ssort;
    }
    public String getStudent_Sper() {

        return student_Sper;
    }
    public String getStudent_Scre() {

        return student_Scre;
    }
    public String getStudent_Sday() {

        return student_Sday;
    }
    public String getStudent_Syear() {

        return student_Syear;
    }
    public String getStudent_Sclass() {

        return student_Sclass;
    }
    public String getStudent_Rnum() {

        return student_Rnum;
    }
    public String getStudent_Pnum() {

        return student_Pnum;
    }

    //교수 데이터(값)을 읽어오는 부분
    public String getProfessor_Number() {

        return professor_Number;
    }
    public String getProfessor_Pname() {

        return professor_Pname;
    }
    public String getProfessor_Pposi() {

        return professor_Pposi;
    }
    public String getProfessor_Ptel1() {

        return professor_Ptel1;
    }
    public String getProfessor_Ptel2() {

        return professor_Ptel2;
    }
    public String getProfessor_Ptel3() {

        return professor_Ptel3;
    }
    public String getProfessor_Pemail1() {

        return professor_Pemail1;
    }
    public String getProfessor_Pemail2() {

        return professor_Pemail2;
    }
    public String getProfessor_Proom() {

        return professor_Proom;
    }
    public String getProfessor_Pstatus() {
        return professor_Pstatus;
    }

    //학과 데이터(값)을 읽어오는 부분
    public String getDepartment_Dname() {

        return department_Dname;
    }
    public String getDepartment_Dtel1(){

        return department_Dtel1;
    }
    public String getDepartment_Dtel2(){

        return department_Dtel2;
    }
    public String getDepartment_Dtel3(){

        return department_Dtel3;
    }
    public String getDepartment_Droomnum(){

        return department_Droomnum;
    }

    public String getDepartment_Dstart(){

        return department_Dstart;
    }
    public String getDepartment_Dend(){

        return department_Dend;
    }

    //예약 데이터(값)를 읽어오는 부분
    public String getReservationPeopleNum()
    {

        return reservationPeopleNum;
    }

    public String getReservationRoomNum()
    {

        return reservationRoomNum;
    }

    public String getReservationRentalStartTime()
    {

        return reservationRentalStartTime;
    }

    public String getReservationRentalEndTime()
    {

        return reservationRentalEndTime;
    }

    public String getReservationRentalDate()
    {

        return reservationRentalDate;
    }

    public String getReservationPerposeOfUse()
    {

        return reservationPerposeOfUse;
    }

    public String getReservationBookStatus()
    {

        return reservationBookStatus;
    }

    public String getReservationCancelReason()
    {

        return reservationCancelReason;
    }

    //강의실 데이터(값)을 읽어오는 부분
    public String getLectureRoomCapacity() {
        return lectureRoomCapacity;
    }

    //학생 데이터(값)을 저장하는 부분
    public void setStudent_Name(String student_Name) {

        this.student_Name = student_Name;
    }

    public void setStudent_Snumber(String student_Snumber) {

        this.student_Snumber = student_Snumber;
    }

    public void setStudent_Rnumber(String student_Rnumber) {

        this.student_Rnumber = student_Rnumber;
    }

    public void setStudent_Sub(String student_Sub) {

        this.student_Sub = student_Sub;
    }

    public void setStudent_Stime(String student_Stime) {

        this.student_Stime = student_Stime;
    }

    public void setStudent_Etime(String student_Etime) {

        this.student_Etime = student_Etime;
    }

    public void setStudent_Scode(String student_Scode) {

        this.student_Scode= student_Scode;
    }

    public void setStudent_Sname(String student_Sname) {

        this.student_Sname = student_Sname;
    }

    public void setStudent_Ssort(String student_Ssort) {

        this.student_Ssort = student_Ssort;
    }

    public void setStudent_Sper(String student_Sper) {

        this.student_Sper = student_Sper;
    }

    public void setStudent_Scre(String student_Scre) {

        this.student_Scre = student_Scre;
    }

    public void setStudent_Sday(String student_Sday) {

        this.student_Sday = student_Sday;
    }

    public void setStudent_Syear(String student_Syear) {

        this.student_Syear = student_Syear;
    }

    public void setStudent_Sclass(String student_Sclass) {

        this.student_Sclass = student_Sclass;
    }
    public void setStudent_Rnum(String student_Rnum) {

        this.student_Rnum = student_Rnum;
    }
    public void setStudent_Pnum(String student_Pnum) {

        this.student_Pnum = student_Pnum;
    }
    //교수 데이터(값)을 저장하는 부분
    public void setProfessor_Number(String professor_Number) {

        this.professor_Number = professor_Number;
    }
    public void setProfessor_Pname(String professor_Pname) {

        this.professor_Pname = professor_Pname;
    }
    public void setProfessor_Pposi(String professor_Pposi) {

        this.professor_Pposi = professor_Pposi;
    }
    public void setProfessor_Ptel1(String professor_Ptel1) {

        this.professor_Ptel1 = professor_Ptel1;
    }
    public void setProfessor_Ptel2(String professor_Ptel2) {

        this.professor_Ptel2 = professor_Ptel2;
    }
    public void setProfessor_Ptel3(String professor_Ptel3) {

        this.professor_Ptel3 = professor_Ptel3;
    }

    public void setProfessor_Pemail1(String professor_Pemail1) {

        this.professor_Pemail1 = professor_Pemail1;
    }
    public void setProfessor_Pemail2(String professor_Pemail2) {

        this.professor_Pemail2 = professor_Pemail2;
    }
    public void setProfessor_Proom(String professor_Proom) {

        this.professor_Proom = professor_Proom;
    }
    public void setProfessor_Pstatus(String professor_Pstatus) {
        this.professor_Pstatus = professor_Pstatus;
    }


    //학과 데이터(값)을 저장하는 부분

    public void setDepartment_Dname(String department_Dname) {

        this.department_Dname = department_Dname;
    }
    public void setDepartment_Dtel1(String department_Dtel1) {

        this.department_Dtel1 = department_Dtel1;
    }
    public void setDepartment_Dtel2(String department_Dtel2) {

        this.department_Dtel2 = department_Dtel2;
    }
    public void setDepartment_Dtel3(String department_Dtel3) {

        this.department_Dtel3 = department_Dtel3;
    }
    public void setDepartment_Droomnum(String department_Droomnum) {

        this.department_Droomnum = department_Droomnum;
    }
    public void setDepartment_Dstart(String department_Dstart) {

        this.department_Dstart = department_Dstart;
    }
    public void setDepartment_Dend(String department_Dend) {

        this.department_Dend = department_Dend;
    }

    //예약 데이터(값)를 저장하는 부분
    public void setReservationPeopleNum(String reservationPeopleNum) {

        this.reservationPeopleNum = reservationPeopleNum;
    }

    public void setReservationRoomNum(String reservationRoomNum) {

        this.reservationRoomNum = reservationRoomNum;
    }

    public void setReservationRentalStartTime(String reservationRentalStartTime) {

        this.reservationRentalStartTime = reservationRentalStartTime;
    }

    public void setReservationRentalEndTime(String reservationRentalEndTime) {

        this.reservationRentalEndTime = reservationRentalEndTime;
    }

    public void setReservationRentalDate(String reservationRentalDate) {

        this.reservationRentalDate = reservationRentalDate;
    }

    public void setReservationPerposeOfUse(String reservationPerposeOfUse) {

        this.reservationPerposeOfUse = reservationPerposeOfUse;
    }

    public void setReservationBookStatus(String reservationBookStatus) {

        this.reservationBookStatus = reservationBookStatus;
    }

    public void setReservationCancelReason(String reservationCancelReason) {

        this.reservationCancelReason = reservationCancelReason;
    }
    //강의실 데이터(값)을 저장하는 부분

    public void setLectureRoomCapacity(String lectureRoomCapacity) {
        this.lectureRoomCapacity = lectureRoomCapacity;
    }
}
