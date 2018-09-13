package maria.belyaeva.qa.addressbook.tests;

import maria.belyaeva.qa.addressbook.model.GroupData;
import maria.belyaeva.qa.addressbook.model.Groups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTests extends TestBase {

//    @DataProvider
//    public Iterator<Object[]> validGroups() {
//        List<Object[]> list = new ArrayList<>();
//        list.add(new Object[] {"test1", "header1", "footer1"});
//        list.add(new Object[] {"test2", "header2", "footer2"});
//        list.add((new Object[] {"test3", "header3", "footer3"}));
//        return list.iterator();
//    }

    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
        String line = reader.readLine();

        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
            line = reader.readLine();
        }

        return list.iterator();
    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) {
        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();
//            int max1 = after.stream().max((Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId();
//            group.setId(max1);
//            before.add(group);
//            Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
//            before.sort(byId);
//            after.sort(byId);
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test(enabled = false)
    public void testBadGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test'").withFooter("footer").withHeader("header");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();

        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
