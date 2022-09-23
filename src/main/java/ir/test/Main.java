package ir.test;

import ir.test.entity.Person;
import ir.test.entity.Vacation;
import ir.test.service.PersonService;
import ir.test.service.StartUP;
import ir.test.service.VacationService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {


        StartUP startUP = new StartUP();
        startUP.startupSystem();

        Scanner scanner = new Scanner(System.in);
        do {

            System.out.println("\nPlease select Options");
            System.out.println("1. Show all person");
            System.out.println("2. Create person");
            System.out.println("3. Delete person");
            System.out.println("4. Search of persons");

            System.out.println("5. Creat vacation");
            System.out.println("6. Show all vacation");
            System.out.println("7. Change vacation state");
            System.out.println("8. Exit");

            int option = scanner.nextInt();

            if (option == 1) {

                PersonService personService = new PersonService();
                personService.showAll();

            }

            else if (option == 2) {

                System.out.println("enter id : ");
                int id = scanner.nextInt();

                System.out.println("enter name : ");
                String name = scanner.next();

                System.out.println("enter family : ");
                String family = scanner.next();

                Person person = new Person(id,name,family);
                PersonService personService = new PersonService();
                personService.createPerson(person);

            }
            else if (option == 3) {

                System.out.println("enter id : ");
                int id = scanner.nextInt();

                Person person = new Person(id);
                PersonService personService = new PersonService();
                personService.deletePerson(person);

            }

            else if (option == 4){

                System.out.println("\nplease select search option :");
                System.out.println(" 1 : search by id");
                System.out.println(" 2 : search by family");
                int searchOption = scanner.nextInt();

                PersonService personService = new PersonService();

                    if( searchOption == 1 ) {
                        System.out.println(" please enter personId :");
                        int searchId = scanner.nextInt();

                        personService.searchPersonByID(searchId);

                    }
                    else if ( searchOption == 2 ) {
                        System.out.println(" please enter personFamily :");
                        String searchFamily = scanner.next();

                        personService.searchPersonByFamily(searchFamily);
                    }
                    else
                        System.out.println("please enter true value");
            }

            else if (option == 5) {

                System.out.println("enter person_id : ");
                int id = scanner.nextInt();

                try {

                    Person person = new Person(id);

                    Vacation.VacationState vacationState = Vacation.VacationState.UNCHECK;

                    System.out.println("enter duration : ");
                    int duration = scanner.nextInt();
                    System.out.println("enter year : ");
                    int year = scanner.nextInt();
                    System.out.println("enter month : ");
                    int month = scanner.nextInt();
                    System.out.println("enter day : ");
                    int day = scanner.nextInt();

                    LocalDate localDate = LocalDate.of(year, month, day);

                    Vacation vacation = new Vacation(localDate, duration, person , vacationState);

                    VacationService vacationService = new VacationService();
                    vacationService.createVacation(vacation);

                }

                catch (Exception e) {
                    System.out.println("no id found");

                }
            }

            else if (option == 6){
                VacationService vacationService = new VacationService();
                vacationService.showAllVacation();
            }

            else if (option == 7){
                System.out.println("present vacation : \n");
                VacationService vacationService = new VacationService();
                vacationService.showAllVacation();

                System.out.println("\nplease select which line change the state");
                int line = scanner.nextInt();
                System.out.println("for Confirmed the Vacation State press : 1 \n" +
                        "for Unconfirmed the Vacation State press : 2");

                int changeState = scanner.nextInt();

                if (changeState == 1 || changeState == 2)
                    vacationService.changeVacationState(line,changeState);

                else
                    System.out.println("please enter correct value");

                System.out.println("present vacation : \n");
                vacationService.showAllVacation();
            }


            else if (option == 8){

                System.exit(0);
            }

        }while (true);

    }
}
