package org.xmlunit.assertj;

import org.assertj.core.api.FactoryBasedNavigableIterableAssert;
import org.w3c.dom.Node;

/**
 * Assertion methods for {@link Iterable} of {@link Node}.
 *
 * <p><b>Simple Example</b></p>
 *
 * <pre>
 * import static org.xmlunit.assertj.XmlAssert.assertThat;
 *
 * final String xml = &quot;&lt;a&gt;&lt;b attr=\&quot;abc\&quot;&gt;&lt;/b&gt;&lt;/a&gt;&quot;;
 *
 * assertThat(xml).nodesByXPath("//a/b").haveAttribute("attr").
 * </pre>
 */
public class MultipleNodeAssert extends FactoryBasedNavigableIterableAssert<MultipleNodeAssert, Iterable<Node>, Node, SingleNodeAssert> {

    interface SingleNodeAssertConsumer {
        void accept(SingleNodeAssert t);
    }

    MultipleNodeAssert(Iterable<Node> nodes) {
        super(nodes, MultipleNodeAssert.class, new NodeAssertFactory());
    }

    /**
     * Equivalent for {@link #isNotEmpty()}.
     */
    public MultipleNodeAssert exist() {
        return isNotEmpty();
    }

    /**
     * Equivalent for {@link #isEmpty()}.
     */
    public void notExist() {
        isEmpty();
    }

    /**
     * Verifies that all the actual nodes have attribute with given name.
     * <p>
     * If the actual nodes iterable is empty, this assertion succeeds as there is no elements to check.
     *
     * @throws AssertionError if the actual nodes iterable is {@code null}.
     * @throws AssertionError if one or more nodes don't have attribute with given name.
     */
    public MultipleNodeAssert haveAttribute(final String attributeName) {
        isNotNull();

        allSatisfy(new SingleNodeAssertConsumer() {
            @Override
            public void accept(SingleNodeAssert singleNodeAssert) {
                singleNodeAssert.hasAttribute(attributeName);
            }
        });

        return this;
    }

    /**
     * Verifies that all the actual nodes have attribute with given name and value.
     * <p>
     * If the actual nodes iterable is empty, this assertion succeeds as there is no elements to check.
     *
     * @throws AssertionError if the actual nodes iterable is {@code null}.
     * @throws AssertionError if one or more nodes don't have attribute with given name and value.
     */
    public MultipleNodeAssert haveAttribute(final String attributeName, final String attributeValue) {
        isNotNull();

        allSatisfy(new SingleNodeAssertConsumer() {
            @Override
            public void accept(SingleNodeAssert singleNodeAssert) {
                singleNodeAssert.hasAttribute(attributeName, attributeValue);
            }
        });

        return this;
    }

    /**
     * Verifies that all the actual nodes don't have attribute with given name.
     * <p>
     * If the actual nodes iterable is empty, this assertion succeeds as there is no elements to check.
     *
     * @throws AssertionError if the actual nodes iterable is {@code null}.
     * @throws AssertionError if any node has attribute with given name.
     */
    public MultipleNodeAssert haveNotAttribute(final String attributeName) {
        isNotNull();

        allSatisfy(new SingleNodeAssertConsumer() {
            @Override
            public void accept(SingleNodeAssert singleNodeAssert) {
                singleNodeAssert.hasNotAttribute(attributeName);
            }
        });

        return this;
    }

    /**
     * Verifies that all the actual nodes don't have attribute with given name and value.
     * <p>
     * If the actual nodes iterable is empty, this assertion succeeds as there is no elements to check.
     *
     * @throws AssertionError if the actual nodes iterable is {@code null}.
     * @throws AssertionError if any node has attribute with given name and value.
     */
    public MultipleNodeAssert haveNotAttribute(final String attributeName, final String attributeValue) {
        isNotNull();

        allSatisfy(new SingleNodeAssertConsumer() {
            @Override
            public void accept(SingleNodeAssert singleNodeAssert) {
                singleNodeAssert.hasNotAttribute(attributeName, attributeValue);
            }
        });

        return this;
    }

    private void allSatisfy(SingleNodeAssertConsumer consumer) {
        int index = 0;
        for (Node node : actual) {
            final SingleNodeAssert singleNodeAssert = toAssert(node, navigationDescription("check node at index " + index));
            consumer.accept(singleNodeAssert);
            index++;
        }
    }
}
