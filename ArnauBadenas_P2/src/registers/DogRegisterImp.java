package registers;

import dogs.Dog;
import dogs.DogID;
import dogs.DogPurpose;

import java.util.*;

public class DogRegisterImp implements DogRegister{

    private final Map<String, SortedSet<Dog>> owners;

    public DogRegisterImp(){
        owners = new HashMap<>();
    }


    @Override
    public boolean registerOwner(String owner) {

        if(owners.containsKey(owner)) return false;
        owners.put(owner, new TreeSet<>());
        return true;

    }

    @Override
    public boolean registerDog(String owner, Dog dog) throws UnknownOwnerException,DifferentOwnerException{
        if(!owners.containsKey(owner)) throw new UnknownOwnerException("El owner especificat no ha sigut registrat");

        String dogOwner = findOwner(dog.getId());
        if(dogOwner != null && !owner.equals(dogOwner)) throw new DifferentOwnerException();

        if(getCurrentRegisteredDogs(owner).contains(dog)){
            return false;
        }
        getCurrentRegisteredDogs(owner).add(dog);
        return true;
    }

    @Override
    public String findOwner(DogID id) {
        for (String owner: owners.keySet()){
            for(Dog dog : owners.get(owner)){
                if (dog.getId().equals(id)){
                    return owner;
                }
            }
        }

        return null;
    }

    /*
    * Funció privada editable
    * */
    private SortedSet<Dog> getCurrentRegisteredDogs(String owner) {
        return owners.get(owner);
    }
    /*
    * Funció pública que retorna copia, per evitar la edició de les dades originals desde fora de la classe.
    * */
    @Override
    public SortedSet<Dog> registeredDogs(String owner) {
        return new TreeSet<>(getCurrentRegisteredDogs(owner));
    }

    @Override
    public SortedSet<String> findPurposeOwners(DogPurpose purpose) {

        SortedSet<String> purposeOwners = new TreeSet<>();

        for (String owner : owners.keySet()){
            boolean found = false;
            Iterator<Dog> iterator = owners.get(owner).iterator();
            while(iterator.hasNext() && !found){
                if(iterator.next().getType().equals(purpose)){
                    purposeOwners.add(owner);
                    found=true;
                }
            }
        }
        return purposeOwners;
    }
}
