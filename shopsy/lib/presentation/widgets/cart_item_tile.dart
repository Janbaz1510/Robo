import 'package:flutter/material.dart';

import '../../domain/entities/cart_item.dart';

class CartItemTile extends StatelessWidget {
  final CartItem item;
  final VoidCallback? onIncrement;
  final VoidCallback? onDecrement;
  final VoidCallback? onRemove;

  const CartItemTile({super.key, required this.item, this.onIncrement, this.onDecrement, this.onRemove});

  @override
  Widget build(BuildContext context) {
    return Card(
      child: Padding(
        padding: const EdgeInsets.all(12.0),
        child: Row(
          children: [
            SizedBox(
              width: 56,
              height: 56,
              child: Image.network(item.product.imageUrl, fit: BoxFit.cover),
            ),
            const SizedBox(width: 12),
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(item.product.title, style: Theme.of(context).textTheme.titleSmall),
                  const SizedBox(height: 4),
                  Text('\$${item.product.price.toStringAsFixed(2)}'),
                ],
              ),
            ),
            Row(
              mainAxisSize: MainAxisSize.min,
              children: [
                IconButton(icon: const Icon(Icons.remove), onPressed: onDecrement),
                Text(item.quantity.toString()),
                IconButton(icon: const Icon(Icons.add), onPressed: onIncrement),
                IconButton(icon: const Icon(Icons.delete_outline), onPressed: onRemove),
              ],
            ),
          ],
        ),
      ),
    );
  }
}