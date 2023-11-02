import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.statement.Statement;
import components.statement.StatementKernel.Condition;
import components.statement.StatementKernel.Kind;
import components.utilities.Tokenizer;

/**
 * JUnit test fixture for {@code Statement}'s constructor and kernel methods.
 *
 * @author Wayne Heym
 * @author Nicholas McCracken and Jack Mikesell
 *
 */
public abstract class StatementTest {

    /**
     * The name of file 1 containing a sequence of BL statements.
     */
    private static final String FILE_NAME_1 = "data/statement-sample-1.bl";
    /**
     * The name of file 2 containing a BL program.
     */
    private static final String FILE_NAME_2 = "data/statement-sample-2.bl";
    /**
     * The name of file 3 containing a BL program.
     */
    private static final String FILE_NAME_3 = "data/statement-sample-3.bl";
    /**
     * The name of file 4 containing a BL program.
     */
    private static final String FILE_NAME_4 = "data/statement-sample-4.bl";
    /**
     * The name of file 5 containing a BL program.
     */
    private static final String FILE_NAME_5 = "data/statement-sample-5.bl";

    /**
     * Invokes the {@code Statement} constructor for the implementation under
     * test and returns the result.
     *
     * @return the new statement
     * @ensures constructor = compose((BLOCK, ?, ?), <>)
     */
    protected abstract Statement constructorTest();

    /**
     * Invokes the {@code Statement} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new statement
     * @ensures constructor = compose((BLOCK, ?, ?), <>)
     */
    protected abstract Statement constructorRef();

    /**
     *
     * Creates and returns a block {@code Statement}, of the type of the
     * implementation under test, from the file with the given name.
     *
     * @param filename
     *            the name of the file to be parsed for the sequence of
     *            statements to go in the block statement
     * @return the constructed block statement
     * @ensures <pre>
     * createFromFile = [the block statement containing the statements
     * parsed from the file]
     * </pre>
     */
    private Statement createFromFileTest(String filename) {
        Statement s = this.constructorTest();
        SimpleReader file = new SimpleReader1L(filename);
        Queue<String> tokens = Tokenizer.tokens(file);
        s.parseBlock(tokens);
        file.close();
        return s;
    }

    /**
     *
     * Creates and returns a block {@code Statement}, of the reference
     * implementation type, from the file with the given name.
     *
     * @param filename
     *            the name of the file to be parsed for the sequence of
     *            statements to go in the block statement
     * @return the constructed block statement
     * @ensures <pre>
     * createFromFile = [the block statement containing the statements
     * parsed from the file]
     * </pre>
     */
    private Statement createFromFileRef(String filename) {
        Statement s = this.constructorRef();
        SimpleReader file = new SimpleReader1L(filename);
        Queue<String> tokens = Tokenizer.tokens(file);
        s.parseBlock(tokens);
        file.close();
        return s;
    }

    /**
     * Test constructor.
     */
    @Test
    public final void testConstructor() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();

        /*
         * The call
         */
        Statement sTest = this.constructorTest();

        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
    }

    /**
     * Test kind of a if statement on file 2.
     */
    @Test
    public final void testKindIf() {
        /*
         * Setup
         */
        final int ifPos = 1;
        Statement sourceTest = this.createFromFileTest(FILE_NAME_2);
        Statement sourceRef = this.createFromFileRef(FILE_NAME_2);
        Statement sTest = sourceTest.removeFromBlock(ifPos);
        Statement sRef = sourceRef.removeFromBlock(ifPos);
        Kind kRef = sRef.kind();

        /*
         * The call
         */
        Kind kTest = sTest.kind();

        /*
         * Evaluation
         */
        assertEquals(kRef, kTest);
        assertEquals(sRef, sTest);
    }

    /**
     * Test kind of a WHILE statement on file 1.
     */
    @Test
    public final void testKindWhile() {
        /*
         * Setup
         */
        final int whilePos = 3;
        Statement sourceTest = this.createFromFileTest(FILE_NAME_1);
        Statement sourceRef = this.createFromFileRef(FILE_NAME_1);
        Statement sTest = sourceTest.removeFromBlock(whilePos);
        Statement sRef = sourceRef.removeFromBlock(whilePos);
        Kind kRef = sRef.kind();

        /*
         * The call
         */
        Kind kTest = sTest.kind();

        /*
         * Evaluation
         */
        assertEquals(kRef, kTest);
        assertEquals(sRef, sTest);
    }

    /**
     * Test kind of a if statement on file 3.
     */
    @Test
    public final void testKindIf2() {
        /*
         * Setup
         */
        final int ifPos = 0;
        Statement sourceTest = this.createFromFileTest(FILE_NAME_2);
        Statement sourceRef = this.createFromFileRef(FILE_NAME_2);
        Statement sTest = sourceTest.removeFromBlock(ifPos);
        Statement sRef = sourceRef.removeFromBlock(ifPos);
        Kind kRef = sRef.kind();

        /*
         * The call
         */
        Kind kTest = sTest.kind();

        /*
         * Evaluation
         */
        assertEquals(kRef, kTest);
        assertEquals(sRef, sTest);
    }

    /**
     * Test kind of a if else statement on file 4.
     */
    @Test
    public final void testKindIfElse() {
        /*
         * Setup
         */
        final int ifPos = 0;
        Statement sourceTest = this.createFromFileTest(FILE_NAME_4);
        Statement sourceRef = this.createFromFileRef(FILE_NAME_4);
        Statement sTest = sourceTest.removeFromBlock(ifPos);
        Statement sRef = sourceRef.removeFromBlock(ifPos);
        Kind kRef = sRef.kind();

        /*
         * The call
         */
        Kind kTest = sTest.kind();

        /*
         * Evaluation
         */
        assertEquals(kRef, kTest);
        assertEquals(sRef, sTest);
    }

    /**
     * Test kind of a call statement on file 5.
     */
    @Test
    public final void testKindCall() {
        /*
         * Setup
         */
        final int callPos = 0;
        Statement sourceTest = this.createFromFileTest(FILE_NAME_5);
        Statement sourceRef = this.createFromFileRef(FILE_NAME_5);
        Statement sTest = sourceTest.removeFromBlock(callPos);
        Statement sRef = sourceRef.removeFromBlock(callPos);
        Kind kRef = sRef.kind();

        /*
         * The call
         */
        Kind kTest = sTest.kind();

        /*
         * Evaluation
         */
        assertEquals(kRef, kTest);
        assertEquals(sRef, sTest);
    }

    /**
     * Test kind of a block statement on file 4.
     */
    @Test
    public final void testKindBlock() {
        /*
         * Setup
         */
        final int blockPos = 1;
        Statement sourceTest = this.createFromFileTest(FILE_NAME_5);
        Statement sourceRef = this.createFromFileRef(FILE_NAME_5);
        Statement sTest = sourceTest.removeFromBlock(blockPos);
        Statement sRef = sourceRef.removeFromBlock(blockPos);
        Kind kRef = sRef.kind();

        /*
         * The call
         */
        Kind kTest = sTest.kind();

        /*
         * Evaluation
         */
        assertEquals(kRef, kTest);
        assertEquals(sRef, sTest);
    }

    /**
     * Test addToBlock at an interior position.
     */
    @Test
    public final void testAddToBlockInterior() {
        /*
         * Setup
         */
        Statement sTest = this.createFromFileTest(FILE_NAME_1);
        Statement sRef = this.createFromFileRef(FILE_NAME_1);
        Statement emptyBlock = sRef.newInstance();
        Statement nestedTest = sTest.removeFromBlock(1);
        Statement nestedRef = sRef.removeFromBlock(1);
        sRef.addToBlock(2, nestedRef);

        /*
         * The call
         */
        sTest.addToBlock(2, nestedTest);

        /*
         * Evaluation
         */
        assertEquals(emptyBlock, nestedTest);
        assertEquals(sRef, sTest);
    }

    /**
     * Test addToBlock at the front.
     */
    @Test
    public final void testAddToBlockFront() {
        /*
         * Setup
         */
        Statement sTest = this.createFromFileTest(FILE_NAME_2);
        Statement sRef = this.createFromFileRef(FILE_NAME_2);
        Statement emptyBlock = sRef.newInstance();
        Statement nestedTest = sTest.removeFromBlock(2);
        Statement nestedRef = sRef.removeFromBlock(2);
        sRef.addToBlock(0, nestedRef);

        /*
         * The call
         */
        sTest.addToBlock(0, nestedTest);

        /*
         * Evaluation
         */
        assertEquals(emptyBlock, nestedTest);
        assertEquals(sRef, sTest);
    }

    /**
     * Test addToBlock at the front.
     */
    @Test
    public final void testAddToBlockFront2() {
        /*
         * Setup
         */
        Statement sTest = this.createFromFileTest(FILE_NAME_3);
        Statement sRef = this.createFromFileRef(FILE_NAME_3);
        Statement emptyBlock = sRef.newInstance();
        Statement nestedTest = sTest.removeFromBlock(0);
        Statement nestedRef = sRef.removeFromBlock(0);
        sRef.addToBlock(0, nestedRef);

        /*
         * The call
         */
        sTest.addToBlock(0, nestedTest);

        /*
         * Evaluation
         */
        assertEquals(emptyBlock, nestedTest);
        assertEquals(sRef, sTest);
    }

    /**
     * Test addToBlock at an interior position.
     */
    @Test
    public final void testAddToBlockInterior2() {
        /*
         * Setup
         */
        Statement sTest = this.createFromFileTest(FILE_NAME_1);
        Statement sRef = this.createFromFileRef(FILE_NAME_1);
        Statement emptyBlock = sRef.newInstance();
        Statement nestedTest = sTest.removeFromBlock(1);
        Statement nestedRef = sRef.removeFromBlock(1);
        sRef.addToBlock(2, nestedRef);

        /*
         * The call
         */
        sTest.addToBlock(2, nestedTest);

        /*
         * Evaluation
         */
        assertEquals(emptyBlock, nestedTest);
        assertEquals(sRef, sTest);
    }

    /**
     * Test addToBlock at the front multiple times.
     */
    @Test
    public final void testAddToBlockFront3() {
        /*
         * Setup
         */
        Statement sTest = this.createFromFileTest(FILE_NAME_1);
        Statement sRef = this.createFromFileRef(FILE_NAME_1);
        Statement emptyBlock = sRef.newInstance();
        Statement nestedTest1 = sTest.removeFromBlock(0);
        Statement nestedTest2 = sTest.removeFromBlock(0);
        Statement nestedRef1 = sRef.removeFromBlock(0);
        Statement nestedRef2 = sRef.removeFromBlock(0);
        sRef.addToBlock(0, nestedRef2);
        sRef.addToBlock(0, nestedRef1);

        /*
         * The call
         */
        sTest.addToBlock(0, nestedTest2);
        sTest.addToBlock(0, nestedTest1);

        /*
         * Evaluation
         */
        assertEquals(emptyBlock, nestedTest1);
        assertEquals(emptyBlock, nestedTest2);
        assertEquals(sRef, sTest);
    }

    /**
     * Test addToBlock at an interior position multiple times.
     */
    @Test
    public final void testAddToBlockInterior3() {
        /*
         * Setup
         */
        Statement sTest = this.createFromFileTest(FILE_NAME_1);
        Statement sRef = this.createFromFileRef(FILE_NAME_1);
        Statement emptyBlock = sRef.newInstance();
        Statement nestedTest1 = sTest.removeFromBlock(1);
        Statement nestedTest2 = sTest.removeFromBlock(1);
        Statement nestedRef1 = sRef.removeFromBlock(1);
        Statement nestedRef2 = sRef.removeFromBlock(1);
        sRef.addToBlock(2, nestedRef2);
        sRef.addToBlock(2, nestedRef1);

        /*
         * The call
         */
        sTest.addToBlock(2, nestedTest2);
        sTest.addToBlock(2, nestedTest1);

        /*
         * Evaluation
         */
        assertEquals(emptyBlock, nestedTest1);
        assertEquals(emptyBlock, nestedTest2);
        assertEquals(sRef, sTest);
    }

    /**
     * Test removeFromBlock at the front leaving a non-empty block behind.
     */
    @Test
    public final void testRemoveFromBlockFrontOnceLeavingNonEmpty() {
        /*
         * Setup
         */
        Statement sTest = this.createFromFileTest(FILE_NAME_1);
        Statement sRef = this.createFromFileRef(FILE_NAME_1);
        Statement nestedRef = sRef.removeFromBlock(0);

        /*
         * The call
         */
        Statement nestedTest = sTest.removeFromBlock(0);

        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
        assertEquals(nestedRef, nestedTest);
    }

    /**
     * Test removeFromBlock at the front multiple times leaving a non-empty
     * block behind.
     */
    @Test
    public final void testRemoveFromBlockFrontManyLeavingNonEmpty() {
        /*
         * Setup
         */
        Statement sTest = this.createFromFileTest(FILE_NAME_5);
        Statement sRef = this.createFromFileRef(FILE_NAME_5);
        Statement nestedRef1 = sRef.removeFromBlock(0);
        Statement nestedRef2 = sRef.removeFromBlock(0);
        Statement nestedRef3 = sRef.removeFromBlock(0);

        /*
         * The call
         */
        Statement nestedTest1 = sTest.removeFromBlock(0);
        Statement nestedTest2 = sTest.removeFromBlock(0);
        Statement nestedTest3 = sTest.removeFromBlock(0);

        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
        assertEquals(nestedRef1, nestedTest1);
        assertEquals(nestedRef2, nestedTest2);
        assertEquals(nestedRef3, nestedTest3);

    }

    /**
     * Test removeFromBlock at the front once leaving an empty block behind.
     */
    @Test
    public final void testRemoveFromBlockFrontOnceLeavingEmpty() {
        /*
         * Setup
         */
        Statement sTest = this.createFromFileTest(FILE_NAME_3);
        Statement sRef = this.createFromFileRef(FILE_NAME_3);
        Statement nestedRef = sRef.removeFromBlock(0);

        /*
         * The call
         */
        Statement nestedTest = sTest.removeFromBlock(0);

        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
        assertEquals(nestedRef, nestedTest);
    }

    /**
     * Test removeFromBlock at the front multiple times leaving an empty block
     * behind.
     */
    @Test
    public final void testRemoveFromBlockFrontManyLeavingEmpty() {
        /*
         * Setup
         */
        Statement sTest = this.createFromFileTest(FILE_NAME_1);
        Statement sRef = this.createFromFileRef(FILE_NAME_1);
        Statement nestedRef1 = sRef.removeFromBlock(0);
        Statement nestedRef2 = sRef.removeFromBlock(0);
        Statement nestedRef3 = sRef.removeFromBlock(0);
        Statement nestedRef4 = sRef.removeFromBlock(0);
        Statement nestedRef5 = sRef.removeFromBlock(0);

        /*
         * The call
         */
        Statement nestedTest1 = sTest.removeFromBlock(0);
        Statement nestedTest2 = sTest.removeFromBlock(0);
        Statement nestedTest3 = sTest.removeFromBlock(0);
        Statement nestedTest4 = sTest.removeFromBlock(0);
        Statement nestedTest5 = sTest.removeFromBlock(0);

        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
        assertEquals(nestedRef1, nestedTest1);
        assertEquals(nestedRef2, nestedTest2);
        assertEquals(nestedRef3, nestedTest3);
        assertEquals(nestedRef4, nestedTest4);
        assertEquals(nestedRef5, nestedTest5);
    }

    /**
     * Test lengthOfBlock, greater than zero.
     */
    @Test
    public final void testLengthOfBlockNonEmpty() {
        /*
         * Setup
         */
        Statement sTest = this.createFromFileTest(FILE_NAME_1);
        Statement sRef = this.createFromFileRef(FILE_NAME_1);
        int lengthRef = sRef.lengthOfBlock();

        /*
         * The call
         */
        int lengthTest = sTest.lengthOfBlock();

        /*
         * Evaluation
         */
        assertEquals(lengthRef, lengthTest);
        assertEquals(sRef, sTest);
    }

    /**
     * Test assembleIf.
     */
    @Test
    public final void testAssembleIf() {
        /*
         * Setup
         */
        Statement blockTest = this.createFromFileTest(FILE_NAME_1);
        Statement blockRef = this.createFromFileRef(FILE_NAME_1);
        Statement emptyBlock = blockRef.newInstance();
        Statement sourceTest = blockTest.removeFromBlock(1);
        Statement sRef = blockRef.removeFromBlock(1);
        Statement nestedTest = sourceTest.newInstance();
        Condition c = sourceTest.disassembleIf(nestedTest);
        Statement sTest = sourceTest.newInstance();

        /*
         * The call
         */
        sTest.assembleIf(c, nestedTest);

        /*
         * Evaluation
         */
        assertEquals(emptyBlock, nestedTest);
        assertEquals(sRef, sTest);
    }

    /**
     * Test assembleIf on file 2.
     */
    @Test
    public final void testAssembleIf2() {
        /*
         * Setup
         */
        Statement blockTest = this.createFromFileTest(FILE_NAME_2);
        Statement blockRef = this.createFromFileRef(FILE_NAME_2);
        Statement emptyBlock = blockRef.newInstance();
        Statement sourceTest = blockTest.removeFromBlock(1);
        Statement sRef = blockRef.removeFromBlock(1);
        Statement nestedTest = sourceTest.newInstance();
        Condition c = sourceTest.disassembleIf(nestedTest);
        Statement sTest = sourceTest.newInstance();

        /*
         * The call
         */
        sTest.assembleIf(c, nestedTest);

        /*
         * Evaluation
         */
        assertEquals(emptyBlock, nestedTest);
        assertEquals(sRef, sTest);
    }

    /**
     * Test assembleIf on file 3.
     */
    @Test
    public final void testAssembleIf3() {
        /*
         * Setup
         */
        Statement blockTest = this.createFromFileTest(FILE_NAME_3);
        Statement blockRef = this.createFromFileRef(FILE_NAME_3);
        Statement emptyBlock = blockRef.newInstance();
        Statement sourceTest = blockTest.removeFromBlock(0);
        Statement sRef = blockRef.removeFromBlock(0);
        Statement nestedTest = sourceTest.newInstance();
        Condition c = sourceTest.disassembleIf(nestedTest);
        Statement sTest = sourceTest.newInstance();

        /*
         * The call
         */
        sTest.assembleIf(c, nestedTest);

        /*
         * Evaluation
         */
        assertEquals(emptyBlock, nestedTest);
        assertEquals(sRef, sTest);
    }

    /**
     * Test disassembleIf.
     */
    @Test
    public final void testDisassembleIf() {
        /*
         * Setup
         */
        Statement blockTest = this.createFromFileTest(FILE_NAME_1);
        Statement blockRef = this.createFromFileRef(FILE_NAME_1);
        Statement sTest = blockTest.removeFromBlock(1);
        Statement sRef = blockRef.removeFromBlock(1);
        Statement nestedTest = sTest.newInstance();
        Statement nestedRef = sRef.newInstance();
        Condition cRef = sRef.disassembleIf(nestedRef);

        /*
         * The call
         */
        Condition cTest = sTest.disassembleIf(nestedTest);

        /*
         * Evaluation
         */
        assertEquals(nestedRef, nestedTest);
        assertEquals(sRef, sTest);
        assertEquals(cRef, cTest);
    }

    /**
     * Test disassembleIf on file 2.
     */
    @Test
    public final void testDisassembleIf2() {
        /*
         * Setup
         */
        Statement blockTest = this.createFromFileTest(FILE_NAME_2);
        Statement blockRef = this.createFromFileRef(FILE_NAME_2);
        Statement sTest = blockTest.removeFromBlock(1);
        Statement sRef = blockRef.removeFromBlock(1);
        Statement nestedTest = sTest.newInstance();
        Statement nestedRef = sRef.newInstance();
        Condition cRef = sRef.disassembleIf(nestedRef);

        /*
         * The call
         */
        Condition cTest = sTest.disassembleIf(nestedTest);

        /*
         * Evaluation
         */
        assertEquals(nestedRef, nestedTest);
        assertEquals(sRef, sTest);
        assertEquals(cRef, cTest);
    }

    /**
     * Test disassembleIf on file 3.
     */
    @Test
    public final void testDisassembleIf3() {
        /*
         * Setup
         */
        Statement blockTest = this.createFromFileTest(FILE_NAME_2);
        Statement blockRef = this.createFromFileRef(FILE_NAME_2);
        Statement sTest = blockTest.removeFromBlock(1);
        Statement sRef = blockRef.removeFromBlock(1);
        Statement nestedTest = sTest.newInstance();
        Statement nestedRef = sRef.newInstance();
        Condition cRef = sRef.disassembleIf(nestedRef);

        /*
         * The call
         */
        Condition cTest = sTest.disassembleIf(nestedTest);

        /*
         * Evaluation
         */
        assertEquals(nestedRef, nestedTest);
        assertEquals(sRef, sTest);
        assertEquals(cRef, cTest);
    }

    /**
     * Test assembleIfElse.
     */
    @Test
    public final void testAssembleIfElse() {
        /*
         * Setup
         */
        final int ifElsePos = 2;
        Statement blockTest = this.createFromFileTest(FILE_NAME_1);
        Statement blockRef = this.createFromFileRef(FILE_NAME_1);
        Statement emptyBlock = blockRef.newInstance();
        Statement sourceTest = blockTest.removeFromBlock(ifElsePos);
        Statement sRef = blockRef.removeFromBlock(ifElsePos);
        Statement thenBlockTest = sourceTest.newInstance();
        Statement elseBlockTest = sourceTest.newInstance();
        Condition cTest = sourceTest.disassembleIfElse(thenBlockTest,
                elseBlockTest);
        Statement sTest = blockTest.newInstance();

        /*
         * The call
         */
        sTest.assembleIfElse(cTest, thenBlockTest, elseBlockTest);

        /*
         * Evaluation
         */
        assertEquals(emptyBlock, thenBlockTest);
        assertEquals(emptyBlock, elseBlockTest);
        assertEquals(sRef, sTest);
    }

    /**
     * Test assembleIfElse on file 4.
     */
    @Test
    public final void testAssembleIfElse2() {
        /*
         * Setup
         */
        final int ifElsePos = 0;
        Statement blockTest = this.createFromFileTest(FILE_NAME_4);
        Statement blockRef = this.createFromFileRef(FILE_NAME_4);
        Statement emptyBlock = blockRef.newInstance();
        Statement sourceTest = blockTest.removeFromBlock(ifElsePos);
        Statement sRef = blockRef.removeFromBlock(ifElsePos);
        Statement thenBlockTest = sourceTest.newInstance();
        Statement elseBlockTest = sourceTest.newInstance();
        Condition cTest = sourceTest.disassembleIfElse(thenBlockTest,
                elseBlockTest);
        Statement sTest = blockTest.newInstance();

        /*
         * The call
         */
        sTest.assembleIfElse(cTest, thenBlockTest, elseBlockTest);

        /*
         * Evaluation
         */
        assertEquals(emptyBlock, thenBlockTest);
        assertEquals(emptyBlock, elseBlockTest);
        assertEquals(sRef, sTest);
    }

    /**
     * Test disassembleIfElse.
     */
    @Test
    public final void testDisassembleIfElse() {
        /*
         * Setup
         */
        final int ifElsePos = 2;
        Statement blockTest = this.createFromFileTest(FILE_NAME_1);
        Statement blockRef = this.createFromFileRef(FILE_NAME_1);
        Statement sTest = blockTest.removeFromBlock(ifElsePos);
        Statement sRef = blockRef.removeFromBlock(ifElsePos);
        Statement thenBlockTest = sTest.newInstance();
        Statement elseBlockTest = sTest.newInstance();
        Statement thenBlockRef = sRef.newInstance();
        Statement elseBlockRef = sRef.newInstance();
        Condition cRef = sRef.disassembleIfElse(thenBlockRef, elseBlockRef);

        /*
         * The call
         */
        Condition cTest = sTest.disassembleIfElse(thenBlockTest, elseBlockTest);

        /*
         * Evaluation
         */
        assertEquals(cRef, cTest);
        assertEquals(thenBlockRef, thenBlockTest);
        assertEquals(elseBlockRef, elseBlockTest);
        assertEquals(sRef, sTest);
    }

    /**
     * Test disassembleIfElse on file 4.
     */
    @Test
    public final void testDisassembleIfElse2() {
        /*
         * Setup
         */
        final int ifElsePos = 0;
        Statement blockTest = this.createFromFileTest(FILE_NAME_4);
        Statement blockRef = this.createFromFileRef(FILE_NAME_4);
        Statement sTest = blockTest.removeFromBlock(ifElsePos);
        Statement sRef = blockRef.removeFromBlock(ifElsePos);
        Statement thenBlockTest = sTest.newInstance();
        Statement elseBlockTest = sTest.newInstance();
        Statement thenBlockRef = sRef.newInstance();
        Statement elseBlockRef = sRef.newInstance();
        Condition cRef = sRef.disassembleIfElse(thenBlockRef, elseBlockRef);

        /*
         * The call
         */
        Condition cTest = sTest.disassembleIfElse(thenBlockTest, elseBlockTest);

        /*
         * Evaluation
         */
        assertEquals(cRef, cTest);
        assertEquals(thenBlockRef, thenBlockTest);
        assertEquals(elseBlockRef, elseBlockTest);
        assertEquals(sRef, sTest);
    }

    /**
     * Test assembleWhile.
     */
    @Test
    public final void testAssembleWhile() {
        /*
         * Setup
         */
        Statement blockTest = this.createFromFileTest(FILE_NAME_1);
        Statement blockRef = this.createFromFileRef(FILE_NAME_1);
        Statement emptyBlock = blockRef.newInstance();
        Statement sourceTest = blockTest.removeFromBlock(1);
        Statement sourceRef = blockRef.removeFromBlock(1);
        Statement nestedTest = sourceTest.newInstance();
        Statement nestedRef = sourceRef.newInstance();
        Condition cTest = sourceTest.disassembleIf(nestedTest);
        Condition cRef = sourceRef.disassembleIf(nestedRef);
        Statement sRef = sourceRef.newInstance();
        sRef.assembleWhile(cRef, nestedRef);
        Statement sTest = sourceTest.newInstance();

        /*
         * The call
         */
        sTest.assembleWhile(cTest, nestedTest);

        /*
         * Evaluation
         */
        assertEquals(emptyBlock, nestedTest);
        assertEquals(sRef, sTest);
    }

    /**
     * Test assembleWhile on file 2.
     */
    @Test
    public final void testAssembleWhile2() {
        /*
         * Setup
         */
        Statement blockTest = this.createFromFileTest(FILE_NAME_2);
        Statement blockRef = this.createFromFileRef(FILE_NAME_2);
        Statement emptyBlock = blockRef.newInstance();
        Statement sourceTest = blockTest.removeFromBlock(1);
        Statement sourceRef = blockRef.removeFromBlock(1);
        Statement nestedTest = sourceTest.newInstance();
        Statement nestedRef = sourceRef.newInstance();
        Condition cTest = sourceTest.disassembleIf(nestedTest);
        Condition cRef = sourceRef.disassembleIf(nestedRef);
        Statement sRef = sourceRef.newInstance();
        sRef.assembleWhile(cRef, nestedRef);
        Statement sTest = sourceTest.newInstance();

        /*
         * The call
         */
        sTest.assembleWhile(cTest, nestedTest);

        /*
         * Evaluation
         */
        assertEquals(emptyBlock, nestedTest);
        assertEquals(sRef, sTest);
    }

    /**
     * Test assembleWhile on file 3.
     */
    @Test
    public final void testAssembleWhile3() {
        /*
         * Setup
         */
        Statement blockTest = this.createFromFileTest(FILE_NAME_3);
        Statement blockRef = this.createFromFileRef(FILE_NAME_3);
        Statement emptyBlock = blockRef.newInstance();
        Statement sourceTest = blockTest.removeFromBlock(0);
        Statement sourceRef = blockRef.removeFromBlock(0);
        Statement nestedTest = sourceTest.newInstance();
        Statement nestedRef = sourceRef.newInstance();
        Condition cTest = sourceTest.disassembleIf(nestedTest);
        Condition cRef = sourceRef.disassembleIf(nestedRef);
        Statement sRef = sourceRef.newInstance();
        sRef.assembleWhile(cRef, nestedRef);
        Statement sTest = sourceTest.newInstance();

        /*
         * The call
         */
        sTest.assembleWhile(cTest, nestedTest);

        /*
         * Evaluation
         */
        assertEquals(emptyBlock, nestedTest);
        assertEquals(sRef, sTest);
    }

    /**
     * Test disassembleWhile.
     */
    @Test
    public final void testDisassembleWhile() {
        /*
         * Setup
         */
        final int whilePos = 3;
        Statement blockTest = this.createFromFileTest(FILE_NAME_1);
        Statement blockRef = this.createFromFileRef(FILE_NAME_1);
        Statement sTest = blockTest.removeFromBlock(whilePos);
        Statement sRef = blockRef.removeFromBlock(whilePos);
        Statement nestedTest = sTest.newInstance();
        Statement nestedRef = sRef.newInstance();
        Condition cRef = sRef.disassembleWhile(nestedRef);

        /*
         * The call
         */
        Condition cTest = sTest.disassembleWhile(nestedTest);

        /*
         * Evaluation
         */
        assertEquals(nestedRef, nestedTest);
        assertEquals(sRef, sTest);
        assertEquals(cRef, cTest);
    }

    /**
     * Test disassembleWhile on file 2.
     */
    @Test
    public final void testDisassembleWhile2() {
        /*
         * Setup
         */
        final int whilePos = 5;
        Statement blockTest = this.createFromFileTest(FILE_NAME_2);
        Statement blockRef = this.createFromFileRef(FILE_NAME_2);
        Statement sTest = blockTest.removeFromBlock(whilePos);
        Statement sRef = blockRef.removeFromBlock(whilePos);
        Statement nestedTest = sTest.newInstance();
        Statement nestedRef = sRef.newInstance();
        Condition cRef = sRef.disassembleWhile(nestedRef);

        /*
         * The call
         */
        Condition cTest = sTest.disassembleWhile(nestedTest);

        /*
         * Evaluation
         */
        assertEquals(nestedRef, nestedTest);
        assertEquals(sRef, sTest);
        assertEquals(cRef, cTest);
    }

    /**
     * Test disassembleWhile on file 5.
     */
    @Test
    public final void testDisassembleWhile3() {
        /*
         * Setup
         */
        final int whilePos = 4;
        Statement blockTest = this.createFromFileTest(FILE_NAME_5);
        Statement blockRef = this.createFromFileRef(FILE_NAME_5);
        Statement sTest = blockTest.removeFromBlock(whilePos);
        Statement sRef = blockRef.removeFromBlock(whilePos);
        Statement nestedTest = sTest.newInstance();
        Statement nestedRef = sRef.newInstance();
        Condition cRef = sRef.disassembleWhile(nestedRef);

        /*
         * The call
         */
        Condition cTest = sTest.disassembleWhile(nestedTest);

        /*
         * Evaluation
         */
        assertEquals(nestedRef, nestedTest);
        assertEquals(sRef, sTest);
        assertEquals(cRef, cTest);
    }

    /**
     * Test assembleCall.
     */
    @Test
    public final void testAssembleCall() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef().newInstance();
        Statement sTest = this.constructorTest().newInstance();

        String name = "look-for-something";
        sRef.assembleCall(name);

        /*
         * The call
         */
        sTest.assembleCall(name);

        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
    }

    /**
     * Test assembleCall.
     */
    @Test
    public final void testAssembleCall2() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef().newInstance();
        Statement sTest = this.constructorTest().newInstance();

        String name = "change-direction";
        sRef.assembleCall(name);

        /*
         * The call
         */
        sTest.assembleCall(name);

        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
    }

    /**
     * Test assembleCall.
     */
    @Test
    public final void testAssembleCall3() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef().newInstance();
        Statement sTest = this.constructorTest().newInstance();

        String name = "turn-around";
        sRef.assembleCall(name);

        /*
         * The call
         */
        sTest.assembleCall(name);

        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
    }

    /**
     * Test disassembleCall.
     */
    @Test
    public final void testDisassembleCall() {
        /*
         * Setup
         */
        Statement blockTest = this.createFromFileTest(FILE_NAME_1);
        Statement blockRef = this.createFromFileRef(FILE_NAME_1);
        Statement sTest = blockTest.removeFromBlock(0);
        Statement sRef = blockRef.removeFromBlock(0);
        String nRef = sRef.disassembleCall();

        /*
         * The call
         */
        String nTest = sTest.disassembleCall();

        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
        assertEquals(nRef, nTest);
    }

    /**
     * Test disassembleCall on file 2.
     */
    @Test
    public final void testDisassembleCall2() {
        /*
         * Setup
         */
        Statement blockTest = this.createFromFileTest(FILE_NAME_2);
        Statement blockRef = this.createFromFileRef(FILE_NAME_2);
        Statement sTest = blockTest.removeFromBlock(0);
        Statement sRef = blockRef.removeFromBlock(0);
        String nRef = sRef.disassembleCall();

        /*
         * The call
         */
        String nTest = sTest.disassembleCall();

        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
        assertEquals(nRef, nTest);
    }

    /**
     * Test disassembleCall on file 5.
     */
    @Test
    public final void testDisassembleCall3() {
        /*
         * Setup
         */
        Statement blockTest = this.createFromFileTest(FILE_NAME_5);
        Statement blockRef = this.createFromFileRef(FILE_NAME_5);
        Statement sTest = blockTest.removeFromBlock(2);
        Statement sRef = blockRef.removeFromBlock(2);
        String nRef = sRef.disassembleCall();

        /*
         * The call
         */
        String nTest = sTest.disassembleCall();

        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
        assertEquals(nRef, nTest);
    }
}
