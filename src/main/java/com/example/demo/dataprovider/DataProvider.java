package com.example.demo.dataprovider;

import com.example.demo.model.Education;
import com.example.demo.model.User;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {
    public static List<User> userList = new ArrayList<>();
    public static List<Education> educationList = new ArrayList<>();

    static {
        userList.add(new User(1, "KAMIL", 24, "https://inews.gtimg.com/newsapp_match/0/3581582328/0",
                "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repellendus, non, dolorem, cumque " +
                        "distinctio magni quam expedita velit laborum sunt amet facere tempora ut fuga aliquam ad " +
                        "asperiores voluptatem dolorum! Quasi."));

        educationList.add(new Education(1, 2005, "Secondary school specializing in artistic",
                "Eos, explicabo, nam, tenetur et ab eius deserunt aspernatur ipsum ducimus quibusdam quis voluptatibus."));

        educationList.add(new Education(1, 2009, "First level graduation in Graphic Design",
                "Aspernatur, mollitia, quos maxime eius suscipit sed beatae ducimus quaerat quibusdam " +
                        "perferendis? Iusto, quibusdam asperiores unde repellat."));
    }
}
