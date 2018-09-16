package maria.belyaeva.qa.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData> {
    private Set<GroupData> delegate;

    public Groups(Groups groups) {
        //grab set from passed groups object, create new HashSet
        this.delegate = new HashSet<GroupData>(groups.delegate);
    }

    public Groups() {
        this.delegate = new HashSet<GroupData>();
    }

    public Groups(Collection<GroupData> groups) {
        this.delegate = new HashSet<GroupData>(groups);
    }

    @Override
    protected Set<GroupData> delegate() {
        return delegate;
    }

    public Groups withAdded(GroupData group) {
        //copy
        Groups groups = new Groups(this);
        // copy + group
        groups.add(group);
        return groups;
    }

    public Groups withoutAdded(GroupData group) {
        //copy
        Groups groups = new Groups(this);
        // copy + group
        groups.remove(group);
        return groups;
    }
}
