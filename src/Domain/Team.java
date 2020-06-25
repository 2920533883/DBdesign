package Domain;

public class Team {
    String id;
    String name;
    String esDate;
    String address;
    String coach;
    int num;

    public Team(String id, String name, String esDate, String address, String coach, int num) {
        this.id = id;
        this.name = name;
        this.esDate = esDate;
        this.address = address;
        this.coach = coach;
        this.num = num;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", esDate='" + esDate + '\'' +
                ", address='" + address + '\'' +
                ", coach='" + coach + '\'' +
                ", num=" + num +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEsDate() {
        return esDate;
    }

    public void setEsDate(String esDate) {
        this.esDate = esDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
