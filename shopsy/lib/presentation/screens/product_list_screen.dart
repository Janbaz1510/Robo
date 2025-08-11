import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../../domain/entities/product.dart';
import '../state/cart_controller.dart';
import '../state/product_list_controller.dart';
import '../widgets/product_tile.dart';

class ProductListScreen extends StatefulWidget {
  const ProductListScreen({super.key});

  @override
  State<ProductListScreen> createState() => _ProductListScreenState();
}

class _ProductListScreenState extends State<ProductListScreen> {
  @override
  void initState() {
    super.initState();
    Future.microtask(() => context.read<ProductListController>().load());
  }

  @override
  Widget build(BuildContext context) {
    final controller = context.watch<ProductListController>();
    final cart = context.watch<CartController>();

    return Scaffold(
      appBar: AppBar(
        title: const Text('Shopsy'),
        actions: [
          Stack(
            clipBehavior: Clip.none,
            children: [
              IconButton(
                icon: const Icon(Icons.shopping_cart_outlined),
                onPressed: () => Navigator.pushNamed(context, '/cart'),
              ),
              if (cart.totalItems > 0)
                Positioned(
                  right: 6,
                  top: 6,
                  child: Container(
                    padding: const EdgeInsets.symmetric(horizontal: 6, vertical: 2),
                    decoration: BoxDecoration(
                      color: Colors.redAccent,
                      borderRadius: BorderRadius.circular(10),
                    ),
                    child: Text(
                      cart.totalItems.toString(),
                      style: const TextStyle(color: Colors.white, fontSize: 12),
                    ),
                  ),
                ),
            ],
          ),
        ],
      ),
      body: switch (controller.status) {
        LoadStatus.loading => const Center(child: CircularProgressIndicator()),
        LoadStatus.error => Center(child: Text(controller.errorMessage ?? 'Error')),
        LoadStatus.loaded => ListView.separated(
            padding: const EdgeInsets.all(12),
            itemCount: controller.products.length,
            separatorBuilder: (_, __) => const SizedBox(height: 8),
            itemBuilder: (context, index) {
              final Product product = controller.products[index];
              return ProductTile(
                product: product,
                onTap: () => Navigator.pushNamed(
                  context,
                  '/details',
                  arguments: product.id,
                ),
                onAddToCart: () => cart.add(product),
              );
            },
          ),
        _ => const SizedBox.shrink(),
      },
    );
  }
}