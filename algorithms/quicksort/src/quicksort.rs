mod quicksort {
    pub fn quicksort(arr: &mut [i32], low: usize, high: usize) {
        if low < high {
            let pivot_idx = partition(arr, low, high);

            if pivot_idx > usize::MIN {
                quicksort(arr, low, pivot_idx - 1);
            }
            if pivot_idx < usize::MAX {
                quicksort(arr, pivot_idx + 1, high);
            }
        }
    }

    fn partition(arr: &mut [i32], low: usize, high: usize) -> usize {
        let pivot_idx = low;

        let mut i = pivot_idx;

        for j in i + 1..=high {
            if arr[j] <= arr[pivot_idx] {
                i += 1;
                arr.swap(i, j);
            }
        }

        arr.swap(pivot_idx, i);

        i
    }
}

#[cfg(test)]
mod tests {
    use super::quicksort::*;

    fn assert_vec_eq<T: PartialEq>(vec1: Vec<T>, vec2: Vec<T>) {
        if vec1.len() != vec2.len() {
            assert!(false);
        }

        for i in 0..vec1.len() {
            assert!(vec1[i] == vec2[i]);
        }
    }

    #[test]
    fn already_sorted() {
        let mut v = vec![0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

        let v_len = v.len();

        quicksort(v.as_mut(), 0, v_len - 1);

        assert_vec_eq(v, vec![0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]);
    }

    #[test]
    fn single_element() {
        let mut v = vec![1];

        let v_len = v.len();

        quicksort(v.as_mut(), 0, v_len - 1);

        assert_vec_eq(v, vec![1]);
    }

    #[test]
    fn empty_vector() {
        let mut v: Vec<i32> = vec![];

        let v_len = v.len();

        quicksort(v.as_mut(), 0, v_len);

        assert_vec_eq(v, vec![]);
    }

    #[test]
    fn random_order() {
        let mut v = vec![3, 6, 8, 10, 1, 2, 1];

        let v_len = v.len();

        quicksort(v.as_mut(), 0, v_len - 1);

        assert_vec_eq(v, vec![1, 1, 2, 3, 6, 8, 10]);
    }
    
    #[test]
    fn descending_to_ascending() {
        let mut v = vec![10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0];

        let v_len = v.len();

        quicksort(v.as_mut(), 0, v_len - 1);

        assert_vec_eq(v, vec![0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]);
    }
}
