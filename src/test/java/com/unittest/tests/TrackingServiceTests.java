package com.unittest.tests;
import static org.junit.Assert.*;

import com.unittest.*;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;

public class TrackingServiceTests {

    private TrackingService service;

    @BeforeClass
    public static void before(){
        System.out.println("before class");
    }

    @AfterClass
    public static void after(){
        System.out.println("after class");
    }

    @Before
    public void setUp(){
        System.out.println("before");
        service = new TrackingService(new NotifierStub());
    }

    @After
    public void tearDown(){
        System.out.println("after");
    }

    @Test
    @Category({GoodTestsCategory.class,BadCategory.class})
    public void newTrackingServiceTotalIsZero(){
        assertEquals("Tracking service total was not zero,",0, service.getTotal());
    }

    @Test
    //@Ignore
    @Category(GoodTestsCategory.class)
    public void WhenAddingProteinTotalIncreasedByThatAmount(){
        service.addProtein(10);
        assertEquals(10,service.getTotal());
        assertThat(service.getTotal(),is(10));

        assertThat(service.getTotal(),allOf(is(10),instanceOf(Integer.class)));
    }


    @Test
    @Category(GoodTestsCategory.class)
    public void WhenRemovingProteinTotalRemainsZero(){
        service.removeProtein(10);
        assertEquals(0,service.getTotal());
    }

    @Test
    public void WhenGoalIsMetHistoryIsUpdated() throws InvalidGoalException{

        Mockery context = new Mockery();
        final Notifier mockNotifier = context.mock(Notifier.class);
        service = new TrackingService(mockNotifier);
        context.checking(new Expectations(){{
            oneOf(mockNotifier).send("goal met");
            will(returnValue(true));
        }});




        service.setGoal(5);
        service.addProtein(6);
        HistoryItem result = service.getHistory().get(1);
        assertEquals("sent:goal met",result.getName());

        context.assertIsSatisfied();
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    //@Test(expected=InvalidGoalException.class)
    @Test
    public void WhenGoalIsSetToLessThanZeroExceptionIsThrown() throws InvalidGoalException {
        thrown.expect(InvalidGoalException.class);
        //thrown.expectMessage(contains("Goal"));
        thrown.expectMessage("Goal was less than zero!");
        service.setGoal(-5);
    }

   // @Rule
    //public Timeout timeout = new Timeout(20);


    @Test(timeout=200)
    @Category(GoodTestsCategory.class)
    public void BadTest(){
        for(int i = 0; i < 1000; i++)
            service.addProtein(1);
    }
}
