import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import 'core/di/service_locator.dart';
import 'presentation/screens/cart_screen.dart';
import 'presentation/screens/product_detail_screen.dart';
import 'presentation/screens/product_list_screen.dart';

void main() {
  runApp(const ShopsyApp());
}

class ShopsyApp extends StatelessWidget {
  const ShopsyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: ServiceLocator.providers(),
      child: MaterialApp(
        title: 'Shopsy',
        theme: ThemeData(useMaterial3: true, colorSchemeSeed: Colors.indigo),
        onGenerateRoute: (settings) {
          if (settings.name == '/') {
            return MaterialPageRoute(builder: (_) => const ProductListScreen());
          }
          if (settings.name == '/details') {
            final productId = settings.arguments as String;
            return MaterialPageRoute(builder: (_) => ProductDetailScreen(productId: productId));
          }
          if (settings.name == '/cart') {
            return MaterialPageRoute(builder: (_) => const CartScreen());
          }
          return null;
        },
        initialRoute: '/',
      ),
    );
  }
}