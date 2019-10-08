package works.weave.socks.orders.controllers;

import org.assertj.core.util.Lists;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import works.weave.socks.orders.entities.Item;
import works.weave.socks.orders.resources.NewOrderResource;

import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class OrdersControllerTest {
    @InjectMocks
    OrdersController controller;

    @Test(expected = OrdersController.InvalidOrderException.class)
    public void newOrder_emptyResourceProvided_exceptionThrown() {
        controller.newOrder(new NewOrderResource());
    }

    @Test
    @Ignore // Failing. BUG
    public void calculateTotal_forEmptyItems() {
        float total = controller.calculateTotal(Lists.emptyList());
        assertThat(total, equalTo(0));
    }

    @Test
    public void calculateTotal_forOneItem() {
        float total = controller.calculateTotal(Collections.singletonList(new Item() {{
            setUnitPrice(1);
        }}));
        assertThat(total, equalTo(5.99F));
    }

    @Test
    public void calculateTotal_forOneItemTwice() {
        float total = controller.calculateTotal(Collections.singletonList(new Item() {{
            setUnitPrice(1);
            setQuantity(2);
        }}));
        assertThat(total, equalTo(6.99F));
    }
}