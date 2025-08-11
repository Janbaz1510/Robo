import 'package:flutter/widgets.dart';
import 'package:provider/provider.dart';
import 'package:provider/single_child_widget.dart';

import '../../data/datasources/local_product_data_source.dart';
import '../../data/repositories/cart_repository_impl.dart';
import '../../data/repositories/product_repository_impl.dart';
import '../../domain/repositories/cart_repository.dart';
import '../../domain/repositories/product_repository.dart';
import '../../domain/usecases/get_product_by_id.dart';
import '../../domain/usecases/get_products.dart';
import '../../presentation/state/cart_controller.dart';
import '../../presentation/state/product_list_controller.dart';

class ServiceLocator {
  static List<SingleChildWidget> providers() {
    return [
      // Data sources and repositories
      Provider<LocalProductDataSource>(create: (_) => LocalProductDataSource()),
      ProxyProvider<LocalProductDataSource, ProductRepository>(
        update: (_, dataSource, __) => ProductRepositoryImpl(dataSource),
      ),
      ProxyProvider<ProductRepository, CartRepository>(
        update: (_, productRepo, __) => CartRepositoryImpl(productRepo),
      ),

      // Use cases
      ProxyProvider<ProductRepository, GetProductsUseCase>(
        update: (_, repo, __) => GetProductsUseCase(repo),
      ),
      ProxyProvider<ProductRepository, GetProductByIdUseCase>(
        update: (_, repo, __) => GetProductByIdUseCase(repo),
      ),

      // Controllers
      ChangeNotifierProvider<ProductListController>(
        create: (context) => ProductListController(
          getProductsUseCase: context.read<GetProductsUseCase>(),
        ),
      ),
      ChangeNotifierProvider<CartController>(
        create: (context) => CartController(
          repository: context.read<CartRepository>(),
        )..load(),
      ),
    ];
  }
}