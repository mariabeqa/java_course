package maria.belyaeva.qa.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.*;

public class Users extends ForwardingSet<UserData> {
    private Set<UserData> delegate;

    public Users(Collection<UserData> users) {
        this.delegate = new HashSet<UserData>(users);
    }

    public Users() {
        this.delegate = new HashSet<UserData>();
    }

    @Override
    protected Set<UserData> delegate() {
        return delegate;
    }
}
