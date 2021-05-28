import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class Student{
    String ID, City, Country, groupName, name, TeleNumber;
    LocalDate DoB;

    @Override
    public String toString(){
        return "Student{" + "Name: " +name + ", Telephone Number: "+ TeleNumber + ", City: " + City+ ", Country: "+Country+", Group Name"+groupName+", Date of birth: "+DoB+"}";
    }

    public static Student dataInput(){
        Student student = new Student();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Student's Name: ");
        student.setName(sc.nextLine());

        System.out.print("Enter City: ");
        student.setCity(sc.nextLine());

        System.out.print("Enter Country: ");
        student.setCountry(sc.nextLine());

        System.out.print("Enter Group's Name: ");
        try {
            student.setGroupName(sc.nextLine());
        } catch (GroupException e1) {
            e1.printStackTrace();
            sc.close();
        }

        System.out.print("Enter Date of Birth(yyyy-mm-dd): ");
        String date;
        date = sc.nextLine();
        LocalDate dob = LocalDate.parse(date);
        
        try {
            student.setDateOfBirth(dob);
        } catch (BirthDateException e) {
            e.printStackTrace();
            sc.close();
        }

        System.out.print("Enter Telephone number: ");
        try {
            student.setTelephoneNumber(sc.nextLine());
        } catch (TelephoneNumberException e) {
            e.printStackTrace();
            sc.close();
        }
        return student;

    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getId(){
        return ID;
    }
    public void setId(String ID){
        this.ID = ID;
    }

    public String getCity(){
        return City;
    }
    public void setCity(String City){
        this.City = City;
    }

    public String getCountry(){
        return Country;
    }
    public void setCountry(String Country){
        this.Country = Country;
    }

    public String groupName(){
        return groupName;
    }
    public void setGroupName(String groupName) throws GroupException{
        if(groupName.charAt(0) != 'I' && groupName.charAt(0) != 'T'){
            throw new GroupException("Group name must starts with 'I' or 'T'");
        }
        else if(groupName.length()!=3){
            throw new GroupException("Invalid Group's name");
        }
        else{
            if((groupName.charAt(0) == 'I') && (groupName.charAt(1) == '1' || groupName.charAt(1) == '2' || groupName.charAt(1) == '3' || groupName.charAt(1) == '4' || groupName.charAt(1) == '5') && (Character.isAlphabetic(groupName.charAt(2)))){
                this.groupName = groupName;
            }
            else if((groupName.charAt(0) == 'T') && (groupName.charAt(1) == '1' || groupName.charAt(1) == '2') && (Character.isAlphabetic(groupName.charAt(2)))){
                this.groupName = groupName;
            }
            else{
                throw new GroupException("Invalid Group's name (year number must between 1 to 5 for Engineering and 1 to 2 for Technician and end with Labels)");
            }
        }

        
    }

    public LocalDate getDateOfBirth(){
        return DoB;
    }
    public void setDateOfBirth(LocalDate DoB) throws BirthDateException{
        LocalDate date = LocalDate.now();
        Period diff = Period.between(DoB, date);
        if(diff.getYears()>=15){
            this.DoB = DoB;
        }
        else{
            throw new BirthDateException();
        }
    }

    public String getTelephoneNumber(){
        return TeleNumber;
    }
    public void setTelephoneNumber(String TeleNumber) throws TelephoneNumberException{

        if(TeleNumber.charAt(0) != '0'){
            throw new TelephoneNumberException("Telephone number must follow Cambodia's phone number formats!");
        }
        else if(TeleNumber.matches(".*[a-z].*") || TeleNumber.matches(".*[A-Z].*")){
            throw new TelephoneNumberException("Invalid Phone Number!");
        }
        else if(TeleNumber.length()!=9){
            throw new TelephoneNumberException("Telephone number must follow Cambodia's phone number formats!");
        }
        else{
            this.TeleNumber = TeleNumber;
        } 
    }
}