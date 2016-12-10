package ru.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-p", description = "Contact photo")
    public String photoSrc;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        new JCommander(generator, args);
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        switch (format) {
            case "xml":
                saveAsXml(contacts, new File(file));
                break;
            case "json":
                saveAsJson(contacts, new File(file));
                break;
            default:
                System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Random random = new Random();
            int r = random.ints(1, 100).findFirst().getAsInt();
            contacts.add(new ContactData()
                    .withFirstName(String.format("testNameGen %s", r))
                    .withMiddleName(String.format("testMiddleNameGen %s", r))
                    .withLastName(String.format("testLastNameGen %s", r))
                    .withNickname(String.format("testNicknameGen %s", r))
                    .withMobile("89285681510")
                    .withEmail(String.format("testgen%s@test.com", r))
                    .withBday("10")
                    .withBmonth("June")
                    .withByear("1994")
                    .withPhotoPath(photoSrc));
        }
        return contacts;
    }
}
