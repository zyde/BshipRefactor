package src.test.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(value = Suite.class)
@SuiteClasses(value = { GameStateInitializationTest.class,GridTest.class,NumberGeneratorTest.class })
public class TestSuite {}
