package ru.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UsersMantis extends ForwardingSet<UserData> {

    private Set<UserData> delegate;

    public UsersMantis(UsersMantis users) {
        this.delegate = new HashSet<UserData>(users.delegate);
    }

    public UsersMantis() {
        this.delegate = new HashSet<>();
    }

    public UsersMantis(Collection<UserData> users) {
        this.delegate = new HashSet<UserData>(users);
    }

    @Override
    protected Set<UserData> delegate() {
        return delegate;
    }


}
