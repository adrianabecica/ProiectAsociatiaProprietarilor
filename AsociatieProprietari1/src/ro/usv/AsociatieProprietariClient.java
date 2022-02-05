/**
 * @author Adriana BECICA
 * @grupa 3131b
 * @nr 1
 */
package ro.usv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AsociatieProprietariClient {

    public static void main(String[] args) throws FileNotFoundException {

        IAsociatieProprietariServ1 asoc =new AsociatieProprietariServ();
        String line;
        String[] Result;
        Scanner scanner = new Scanner(new File(args[0]));

        while (scanner.hasNextLine()) {

            int nr = 0;
            long x = 0;
            long y = 0;
            int i = 0;
            int b = 0;

            line = scanner.nextLine();
            Result = line.split("\\s+");

            System.out.println(line);

            Pattern p = Pattern.compile("\"([^\"]*)\"");
            Matcher m = p.matcher(line);


            while (m.find()) {
                y = y + Integer.parseInt(String.valueOf(Arrays.stream(m.group(1).split("\\s+")).count()));
                nr++;
            }

            x = Result.length - y + nr;

            try{
                switch (Result[0]) {

                    case "add":
                        switch (Integer.parseInt(String.valueOf(x))) {

                            case 11://Locuinta

                                if (Result[1].matches("L")) {

                                    p = Pattern.compile("\"([^\"]*)\"");
                                    m = p.matcher(line);

                                    while (m.find()) {
                                        b = b + m.groupCount();
                                        if (b == 1) {
                                            Result[5] = m.group().replace("\"","");
                                            i = 5 + Integer.parseInt(String.valueOf(Arrays.stream(m.group(1).split("\\s+")).count()));
                                        }

                                    }

                                    if (b == 0) {
                                        i = 6;
                                    }

                                    asoc.saveApartament(new Locuinta(Integer.parseInt(Result[2]), Float.parseFloat(Result[3]), Integer.parseInt(Result[4]), Result[5], Integer.parseInt(Result[i]), Result[i + 1].charAt(0), Integer.parseInt(Result[i + 2]), Integer.parseInt(Result[i + 3]), Integer.parseInt(Result[i + 4])));
                                }
                                break;

                            case 12://SediuFirma

                                if (Result[1].matches("SF")) {

                                    p = Pattern.compile("\"([^\"]*)\"");
                                    m = p.matcher(line);

                                    while (m.find()) {
                                        Result[10] = m.group().replace("\"","");
                                        i = 10 + Integer.parseInt(String.valueOf(Arrays.stream(m.group(1).split("\\s+")).count()));
                                    }

                                    asoc.saveApartament(new SediuFirma(Integer.parseInt(Result[2]), Float.parseFloat(Result[3]), Integer.parseInt(Result[4]), Result[5], Integer.parseInt(Result[6]), Result[7].charAt(0), Integer.parseInt(Result[8]), Integer.parseInt(Result[9]), Result[10], Integer.parseInt(Result[i])));

                                }
                                break;

                            default: throw new IllegalArgumentException("Eroare. Numarul parametrilor nu este corect");
                        }
                        break;


                    case "list":

                        if (Result.length == 1) {
                            System.out.println(asoc.getApartamentente());
                        } else if (Result.length == 2) {
                            if (asoc.getApartamentById(Integer.parseInt(Result[1])) == null)
                                throw new IllegalArgumentException("Eroare. Nu exista apartament cu id=" + Result[1]);
                            else
                                System.out.println(asoc.getApartamentById(Integer.parseInt(Result[1])));
                        } else

                            throw new IllegalArgumentException("Eroare. Numarul parametrilor nu este corect");

                        break;

                    case "street":

                        p = Pattern.compile("\"([^\"]*)\"");
                        m = p.matcher(line);

                        while (m.find()) {
                            Result[1] = m.group().replace("\"","");
                        }

                        System.out.println("Ap. str. " + Result[1] + asoc.findIdsByStreet(Result[1]));
                        break;

                    case "clear":
                        if(Result.length>1)
                            throw  new IllegalArgumentException("Eroare. Numarul parametrilor nu este corect");
                        asoc.deleteApartmente();
                        System.out.println("S-au eliminat toate apartamentele");
                        break;

                    case "delete":
                        if (Result.length > 2)
                            throw new IllegalArgumentException("Eroare. Numarul parametrilor nu este corect");
                        if (asoc.getApartamentById(Integer.parseInt(Result[1])) == null)
                            throw new IllegalArgumentException("Eroare. delete: obj cu id= " + Result[1] + " nu exista");
                        asoc.deleteApartment(Integer.parseInt(Result[1]));

                        break;


                    case "file":
                        if (Result.length == 1) {
                            asoc.setStocare(null);
                        }
                        else if (Result.length == 2) {
                            asoc.setStocare(Result[1]);
                        }
                        break;

                    case "rem":
                        System.out.println(line);
                        break;

                    case "stop":
                        System.out.println("La revedere!");
                        System.exit(0);
                        break;


                    case "count":
                        if(Result.length>2)
                            throw new IllegalArgumentException("Eroare. Numarul parametrilor nu este corect");
                        if(Result.length==2)
                        {
                            if(!Result[1].equals("L")&&!Result[1].equals("SF"))
                                System.out.println("Nu sunt apartamente de tipul "+Result[1]);
                            else
                            if(Result[1].equals("L"))
                                System.out.println("Nr.locuinte: "+asoc.countApartamente(Result[1]));
                            else
                            if(Result[1].equals("SF"))
                                System.out.println("Nr.sedii firme: "+asoc.countApartamente(Result[1]));

                        }
                        else if(Result.length==1)
                        {

                            System.out.println("Nr.apartamente: "+asoc.countApartamente(null));
                        }


                        break;


                    case "newer":
                        if(Result.length>2)
                            throw new IllegalArgumentException("Eroare. Numarul parametrilor nu este corect");
                        System.out.println("Ap. construite cu cel mult " + Integer.parseInt(Result[1]) + " ani in urma: " + asoc.findIdsNewerThan(Integer.parseInt(Result[1])));
                        break;



                }
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }



        }
        scanner.close();

    }
}






