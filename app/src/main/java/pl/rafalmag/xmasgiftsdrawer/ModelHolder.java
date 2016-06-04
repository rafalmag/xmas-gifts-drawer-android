package pl.rafalmag.xmasgiftsdrawer;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Set;

import javax.inject.Singleton;

@Singleton
public class ModelHolder {

    private Model model = new Model();
    private BiMap<Contact, Person> map = HashBiMap.create();

    public void addPerson(Contact contact) {
        Person person = getPerson(contact);
        model.addPerson(person);
    }

    public void removePerson(Contact contact) {
        Person person = getPerson(contact);
        map.remove(contact);
        model.removePerson(person);
    }

    private Person getPerson(Contact contact) {
        Person person = map.get(contact);
        if (person == null) {
            person = new Person("" + contact.get_id());
            map.put(contact, person);
        }
        return person;
    }

    public int getPersonCount(){
        return model.getPersons().size();
    }

    @Override
    public String toString() {
        return "ModelHolder{" +
                "model=\n" + model +
                "\n}";
    }

    public Contact getContact(int i) {
        Person person = Lists.newArrayList(model.getPersons()).get(i);
        return map.inverse().get(person);
    }
}
