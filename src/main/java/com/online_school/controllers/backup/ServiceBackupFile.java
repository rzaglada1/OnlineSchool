package com.online_school.controllers.backup;


import com.online_school.models.AddMaterials;
import com.online_school.models.Homework;
import com.online_school.models.Lecture;
import com.online_school.models.Person;
import com.online_school.utils.log.Log;


import java.io.*;
import java.util.List;


public class ServiceBackupFile {

    String nameLog = "Log OnlineSchool";

    public void createBackup(
            List<AddMaterials> repoAddMaterials
            , List<Homework> repoHomework
            , List<Lecture> repoLecture
            , List<Person> repoPerson
            , long courseID
            , String nameFile) {

        try (FileOutputStream fos = new FileOutputStream(nameFile + courseID);
             ObjectOutputStream ous = new ObjectOutputStream(fos) ) {
            ous.writeObject(new ServiceBackupRepository<AddMaterials>().createRepo(repoAddMaterials, courseID));
            ous.writeObject(new ServiceBackupRepository<Homework>().createRepo(repoHomework, courseID));
            ous.writeObject(new ServiceBackupRepository<Lecture>().createRepo(repoLecture, courseID));
            ous.writeObject(new ServiceBackupRepository<Person>().createRepo(repoPerson, courseID));

        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public void printBackup(long courseID, String nameFile) {
        try (FileInputStream fis = new FileInputStream(nameFile + courseID);
             ObjectInputStream ois = new ObjectInputStream(fis) ) {
            System.out.println("Add materials: ");
            System.out.println(ois.readObject());
            System.out.println("Homework");
            System.out.println(ois.readObject());
            System.out.println("Lecture");
            System.out.println(ois.readObject());
            System.out.println("Person");
            System.out.println(ois.readObject());

        } catch (Exception e) {
            Log.warning(nameLog, "Error data file", e.getStackTrace());
        }
    }

}
