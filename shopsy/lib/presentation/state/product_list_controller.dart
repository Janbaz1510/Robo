import 'package:flutter/foundation.dart';

import '../../domain/entities/product.dart';
import '../../domain/usecases/get_products.dart';

enum LoadStatus { idle, loading, loaded, error }

class ProductListController extends ChangeNotifier {
  final GetProductsUseCase getProductsUseCase;

  LoadStatus status = LoadStatus.idle;
  List<Product> products = const [];
  String? errorMessage;

  ProductListController({required this.getProductsUseCase});

  Future<void> load() async {
    status = LoadStatus.loading;
    errorMessage = null;
    notifyListeners();
    try {
      products = await getProductsUseCase();
      status = LoadStatus.loaded;
    } catch (e) {
      status = LoadStatus.error;
      errorMessage = e.toString();
    }
    notifyListeners();
  }
}