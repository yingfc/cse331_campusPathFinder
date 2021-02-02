package graph.junitTests;

import org.junit.Rule;
import org.junit.rules.Timeout;

public class MyGraphTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested
}
