import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class GroupTest {

    private Group group;
    private MockClient mockClient1;
    private MockClient mockClient2;

    @Before
    public void setUp() {
        group = new Group();
        mockClient1 = new MockClient("Client-1");
        mockClient2 = new MockClient("Client-2");
    }

    @Test
    public void testMemberJoinsGroup() {
        group.addMember(mockClient1);
        assertEquals(1, group.getMembers().size());
    }

    @Test 
    public void testCoordinatorAssignment() {
        group.addMember(mockClient1);
        assertEquals(mockClient1, group.getCoordinator());
    }

    @Test 
    public void testCoordinatorHandover() {
        group.addMember(mockClient1);
        group.addMember(mockClient2);
        group.removeMember(mockClient1);
        assertEquals(mockClient2, group.getCoordinator());
    }

    @Test
    public void testMemberRemoval() {
        group.addMember(mockClient1);
        group.addMember(mockClient2);
        group.removeMember(mockClient1);
        assertEquals(1, group.getMembers().size());
    }

    @Test 
    public void testDeadClientRemoval() {
        group.addMember(mockClient1);
        group.addMember(mockClient2);
        mockClient2.setAlive(false);

        // simulate what the ping thread does
        List<Observer> deadClients = new ArrayList<>();
        for (Observer member : group.getMembers()) {
            if (!member.isAlive()) {
                deadClients.add(member);
            }
        }
        for (Observer dead : deadClients) {
            group.removeMember(dead);
        }

        assertEquals(1, group.getMembers().size());
        assertEquals(mockClient1, group.getCoordinator());
    }
}