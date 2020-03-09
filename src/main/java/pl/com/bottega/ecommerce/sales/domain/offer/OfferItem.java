/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class OfferItem {

    Product product;

    private int quantity;

    public BigDecimal addTotalCost() {
        BigDecimal discountValue = new BigDecimal(0);
        if (discount != null) {
            discountValue = discountValue.add(discount.getDenomination());
        }

        return getProduct().getPrice().getDenomination().multiply(new BigDecimal(quantity)).subtract(discountValue);

    }

    ;

    // discount
    private String discountCause;

    private Money discount;

    public OfferItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public OfferItem(Product product, int quantity, String discountCause, Money discount) {
        this.product = product;
        this.quantity = quantity;
        this.discountCause = discountCause;
        this.discount = discount;

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDiscountCause() {
        return discountCause;
    }

    public void setDiscountCause(String discountCause) {
        this.discountCause = discountCause;
    }

    public Money getDiscount() {
        return discount;
    }

    public void setDiscount(Money discount) {
        this.discount = discount;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        OfferItem offerItem = (OfferItem) o;
        return quantity == offerItem.quantity && Objects.equals(product, offerItem.product) && Objects.equals(addTotalCost(),
                offerItem.addTotalCost()) && Objects.equals(discountCause, offerItem.discountCause) && Objects.equals(discount,
                offerItem.discount);
    }

    @Override public int hashCode() {
        return Objects.hash(product, quantity, addTotalCost(), discountCause, discount);
    }

    /**
     * @param other
     * @param delta acceptable percentage difference
     * @return
     */
    public boolean sameAs(OfferItem other, double delta) {
        if (getProduct().getPrice() == null) {
            if (other.getProduct().getPrice() != null) {
                return false;
            }
        } else if (!getProduct().getPrice().equals(other.getProduct().getPrice())) {
            return false;
        }
        if (getProduct().getProductName() == null) {
            if (other.getProduct().getProductName() != null) {
                return false;
            }
        } else if (!getProduct().getProductName().equals(other.getProduct().getProductName())) {
            return false;
        }

        if (getProduct().getProductId() == null) {
            if (other.getProduct().getProductId() != null) {
                return false;
            }
        } else if (!getProduct().getProductId().equals(other.getProduct().getProductId())) {
            return false;
        }
        if (getProduct().getProductType() == null) {
            if (other.getProduct().getProductType() != null) {
                return false;
            }
        } else if (!getProduct().getProductType().equals(other.getProduct().getProductType())) {
            return false;
        }

        if (quantity != other.quantity) {
            return false;
        }

        BigDecimal max;
        BigDecimal min;
        if (addTotalCost().compareTo(other.addTotalCost()) > 0) {
            max = addTotalCost();
            min = other.addTotalCost();
        } else {
            max = other.addTotalCost();
            min = addTotalCost();
        }

        BigDecimal difference = max.subtract(min);
        BigDecimal acceptableDelta = max.multiply(BigDecimal.valueOf(delta / 100));

        return acceptableDelta.compareTo(difference) > 0;
    }

}
