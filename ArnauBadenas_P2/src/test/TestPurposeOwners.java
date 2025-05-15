package test;

import dogs.Dog;
import dogs.DogID;
import dogs.DogPurpose;
import registers.DogRegister;
import registers.DogRegisterImp;

import java.util.SortedSet;
import java.util.TreeSet;

import static utilsProva.UtilsProva.*;

public class TestPurposeOwners {
    public static void main(String[] args) {

        Dog[] huntingDogs = {
                new Dog("FINN", 40, DogPurpose.HUNTING, "Dante", "Kimbertal"),
                new Dog("FINN", 40, DogPurpose.HUNTING, "Dante", "Altobello"),
                new Dog("FINN", 40, DogPurpose.HUNTING, "Dante", "von Hohenhalle")
        };

        DogID[] unknownIDs = {
                new DogID("Noble", "Camrose"),
                new DogID("Nobl", "Trewater"),
                new DogID("Zeus", "vom Burgimwald"),
                new DogID("Xeus", "Kimbertal"),
                new DogID("Belle", "Vegas du Haut Mansard"),
                new DogID("Disel", "vom Burgimwald"),
        };

        iniciar("Prova 2 reduïda. Només findOwner i findPurposeOwners");

        DogRegister wr = new DogRegisterImp();
        informar("registre creat\n");

        // Register one owner and some hunting dogs
        String owner = "Alan";
        wr.registerOwner(owner);
        for (Dog dog : huntingDogs) {
            wr.registerDog(owner, dog);
        }

        // Test findOwner with unknown dogs
        informar("findOwner per gossos no registrats");
        try {
            for (DogID id : unknownIDs) {
                if (wr.findOwner(id) != null) {
                    notificarError("findOwner ha trobat usuari per a gos no registrat", SORTIR);
                }
            }
        } catch (Exception e) {
            notificarExcepcio(e, SORTIR);
        }
        informar("...findOwner gossos no registrats ha proporcionat els resultats esperats\n");

        // Test findPurposeOwners
        informar("findPurpose owner test");
        try {
            SortedSet<String> result = wr.findPurposeOwners(DogPurpose.HUNTING);
            if (!result.equals(new TreeSet<>() {{
                add(owner);
            }})) {
                throw new Exception("Resultat no esperat");
            }
        } catch (Exception e) {
            notificarExcepcio(e, SORTIR);
        }

        finalitzar();
    }
}
