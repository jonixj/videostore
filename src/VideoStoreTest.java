
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VideoStoreTest {
    private final double delta = 0.001;
    private Statement statement;
    private Movie newReleaseMovie1;
    private Movie newReleaseMovie2;
    private Movie childrensMovie1;
    private Movie regularMovie1;
    private Movie regularMovie2;
    private Movie regularMovie3;

    @Before
    public void setUp() {
        statement = new Statement("Customer");
        newReleaseMovie1 = new Movie("New Release", Movie.NEW_RELEASE);
        newReleaseMovie2 = new Movie("New Release 2", Movie.NEW_RELEASE);
        childrensMovie1 = new Movie("Children's Movie", Movie.CHILDRENS);
        regularMovie1 = new Movie("Regular Movie 1", Movie.REGULAR);
        regularMovie2 = new Movie("Regular Movie 2", Movie.REGULAR);
        regularMovie3 = new Movie("Regular Movie 3", Movie.REGULAR);
    }

    @Test
    public void testSingleNewReleaseStatementTotals() {
        statement.addRental(new Rental(newReleaseMovie1, 3));

        statement.generate();
        Assert.assertEquals(9.0, statement.getTotal(), delta);
        Assert.assertEquals(2, statement.getFrequentRenterPoints());
    }

    @Test
    public void testDualNewReleaseStatementTotals() {
        statement.addRental(new Rental(newReleaseMovie1, 3));
        statement.addRental(new Rental(newReleaseMovie2, 3));

        statement.generate();
        Assert.assertEquals(18.0, statement.getTotal(), delta);
        Assert.assertEquals(4, statement.getFrequentRenterPoints());
    }

    @Test
    public void testSingleChildrensStatementTotals() {
        statement.addRental(new Rental(childrensMovie1, 3));

        statement.generate();
        Assert.assertEquals(1.5, statement.getTotal(), delta);
        Assert.assertEquals(1, statement.getFrequentRenterPoints());
    }

    @Test
    public void testMultipleRegularStatementTotals() {
        statement.addRental(new Rental(regularMovie1, 1));
        statement.addRental(new Rental(regularMovie2, 2));
        statement.addRental(new Rental(regularMovie3, 3));

        statement.generate();
        Assert.assertEquals(7.5, statement.getTotal(), delta);
        Assert.assertEquals(3, statement.getFrequentRenterPoints());
    }

    @Test
    public void testMultipleRegularStatementFormat() {
        statement.addRental(new Rental(regularMovie1, 1));
        statement.addRental(new Rental(regularMovie2, 2));
        statement.addRental(new Rental(regularMovie3, 3));

        Assert.assertEquals(
                "Rental Record for Customer\n\t" +
                        "Regular Movie 1\t2.0\n" +
                        "\tRegular Movie 2\t2.0\n" +
                        "\tRegular Movie 3\t3.5\n" +
                        "You owed 7.5\n" +
                        "You earned 3 frequent renter points\n",
                statement.generate()
        );
    }

}