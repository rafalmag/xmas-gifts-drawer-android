package pl.rafalmag.xmasgiftsdrawer;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Set;

import javax.inject.Singleton;

@Singleton
public class ModelHolder {

    private Model model = new Model();
    private Map<Contact, Person> map = Maps.newHashMap();

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

    @Override
    public String toString() {
        return "ModelHolder{" +
                "model=\n" + model +
                "\n}";
    }
}
