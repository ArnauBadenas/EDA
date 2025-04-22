package registers;

import dogs.Dog;
import dogs.DogID;
import dogs.DogPurpose;

import java.util.*;

public class DogRegisterImp implements DogRegister{

    private Map<String, SortedSet<Dog>> owners = new HashMap<>();

    public DogRegisterImp(){}


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
        if(dogOwner != null && !Objects.equals(owner, dogOwner)) throw new DifferentOwnerException();

        if(this.registeredDogs(owner).contains(dog)){
            return false;
        }
        this.registeredDogs(owner).add(dog);
        return true;
    }

    @Override
    public String findOwner(DogID id) {
        for (Map.Entry<String, SortedSet<Dog>> owner: owners.entrySet()){
            for(Dog dog : owner.getValue()){
                if (dog.getId().equals(id)){
                    return owner.getKey();
                }
            }
        }


        return null;
    }

    @Override
    public SortedSet<Dog> registeredDogs(String owner) {
        return owners.get(owner);
    }

    @Override
    public SortedSet<String> findPurposeOwners(DogPurpose purpose) {
        SortedSet<String> purposeOwners = new TreeSet<>();
        for (Map.Entry<String, SortedSet<Dog>> owner : owners.entrySet()){
            for (Dog dog : owner.getValue()){
                if(dog.getType().equals(purpose)){
                    purposeOwners.add(owner.getKey());
                }
            }
        }
        return purposeOwners;
    }
}
