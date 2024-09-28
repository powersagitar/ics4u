mod improved_bubble_sort {
    #[allow(dead_code)]
    pub fn improved_bubble_sort(array: &mut [i32]) {
        let mut i = 0;
        let mut swapped = true;

        while swapped {
            swapped = false;

            for j in 0..array.len() - i - 1 {
                if array[j] > array[j + 1] {
                    array.swap(j, j + 1);
                    swapped = true;
                }
            }

            i += 1;
        }
    }
}

#[cfg(test)]
mod tests {
    use super::improved_bubble_sort::*;

    fn assert_vec_eq<T: PartialEq>(v1: Vec<T>, v2: Vec<T>) {
        assert_eq!(v1.len(), v2.len());

        for i in 0..v1.len() {
            assert!(v1[i] == v2[i]);
        }
    }

    #[test]
    fn already_sorted() {
        let mut v1 = vec![1, 2, 3, 4, 5, 6];
        improved_bubble_sort(v1.as_mut_slice());
        assert_vec_eq(v1, vec![1, 2, 3, 4, 5, 6]);

        let mut v2 = vec![1, 2];
        improved_bubble_sort(v2.as_mut_slice());
        assert_vec_eq(v2, vec![1, 2]);
    }

    #[test]
    fn single_element() {
        let mut v = vec![1];
        improved_bubble_sort(v.as_mut_slice());
        assert_vec_eq(v, vec![1]);
    }

    #[test]
    fn random_data() {
        let mut v = vec![10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0];
        improved_bubble_sort(v.as_mut_slice());
        assert_vec_eq(v, vec![0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]);
    }
}
